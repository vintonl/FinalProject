import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { catchError, tap } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = environment.baseUrl;
  // private user: User = new User();

  userLoggedIn = false;


  constructor(private http: HttpClient) { }

  login(username, password) {
    this.userLoggedIn = true;
    // this.user = new User();
    // this.getUserByUsername(username).subscribe(data => this.user = data);
    // console.log('in login');
    // console.log(this.user);
    // console.log(username);

    // Make credentials
    const credentials = this.generateBasicAuthCredentials(username, password);
    // Send credentials as Authorization header (this is spring security convention for basic auth)
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    };

    // create request to authenticate credentials
    return this.http
      .get(this.baseUrl + 'authenticate', httpOptions)
      .pipe(
        tap((res) => {
          localStorage.setItem('credentials', credentials);
          localStorage.setItem('username', username);
          return res;
        }),
        catchError((err: any) => {
          console.log(err);
          return throwError('AuthService.login(): Error logging in.');
        })
      );
  }

  register(user) {
    // create request to register a new account

    console.log(user);

    return this.http.post(this.baseUrl + 'register', user)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('AuthService.register(): error registering user.');
        })
      );
  }

  logout() {
    this.userLoggedIn = false;
    // this.user = new User();
    localStorage.removeItem('credentials');
    localStorage.removeItem('username');

  }

  checkLogin() {
    if (localStorage.getItem('credentials')) {
      return true;
    }
    return false;
  }

  generateBasicAuthCredentials(username, password) {
    return btoa(`${username}:${password}`);
  }

  getCredentials() {
    return localStorage.getItem('credentials');
  }

  getLoggedInUsername() {
    return localStorage.getItem('username');
  }
  getUserByUsername(username: string) {
    //  localStorage.getItem();

    console.log('in get user by username method');
    console.log(username);
    const httpOptions = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Headers': 'Content-Type',
        // 'Content-Type': 'application/json',
        Authorization: `Basic ` + this.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      })
    };

    return this.http.get<User>(this.baseUrl + 'api/users/' + username, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('AuthService.register(): error registering user.');
        })
      );
  }

  // getUser(): User {
  //   return this.user;
  // }
}
