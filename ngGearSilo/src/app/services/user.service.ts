import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  // users: User[] = [];
  // newUser = new User();

  private baseUrl = environment.baseUrl;
  private url = environment.baseUrl + 'api/users';

  constructor(
    private http: HttpClient,
    private router: Router,
    private authService: AuthService
  ) { }

  // LIST of USERS **********

  index() {
    if (localStorage.length === 0) {
      this.router.navigateByUrl('/login');
    }
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ` + this.authService.getCredentials(), // // Space after `Basic ` + is key due to concatenation.
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.get<User[]>(this.url, httpOptions).pipe(
      catchError((err: any) => {
        // console.log(err);
        return throwError('user.service.ts Error: Index Method');
      })
    );
  }

  // USER BY ID **********

  public findById(id: number) {
    if (localStorage.length === 0) {
      this.router.navigateByUrl('/login');
    }
    const httpOptions = {
      headers: new HttpHeaders({
        // tslint:disable-next-line: max-line-length
        Authorization: `Basic ` + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      })
    };

    return this.http.get<User>(this.url + '/' + id, httpOptions).pipe(
      catchError((err: any) => {
        // console.log(err);
        return throwError('user.service.ts Error: FindById Method');
      })
    );
  }

  // CREATE USER **********

  create(user: User) {
    if (localStorage.length === 0) {
      this.router.navigateByUrl('/login');
    }
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ` + this.authService.getCredentials(),
        'Content-Type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http
      .post(environment.baseUrl + '/register', user, httpOptions)
      .pipe(
        catchError((err: any) => {
          // console.log(err);
          return throwError('user.service.ts Error: Create Method');
        })
      );
  }

  // UPDATE USER **********

  update(user: User) {
    if (localStorage.length === 0) {
      this.router.navigateByUrl('/login');
    }
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ` + this.authService.getCredentials(),
        'Content-Type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.put(this.url + '/admin', user, httpOptions).pipe(
      catchError((err: any) => {
        // console.log(err);
        // console.log('update method User Service');
        return throwError('user.service.ts Error: Update Method');
      })
    );
  }
}
