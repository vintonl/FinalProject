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
  ) {}

  index() {

    if (localStorage.length === 0) {
      this.router.navigateByUrl('/login');
    }
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ` + this.authService.getCredentials(), //
        'X-Requested-With': 'XMLHttpRequest',
        // 'Content-Type': 'application/json'
      })
    };
    return this.http.get<User[]>(this.url, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Service: Index Method');
      })
    );
  }

  public findById(id: number) {
    if (localStorage.length === 0) {
      this.router.navigateByUrl('/login');
    }
    const httpOptions = {
      headers: new HttpHeaders({
        // tslint:disable-next-line: max-line-length
        Authorization: `Basic ` + this.authService.getCredentials(), // Space after Basic is key due to concatenation
        'X-Requested-With': 'XMLHttpRequest',                        // `Basic ` = good. `Basic` = bad.
        // 'Content-Type': 'application/json'
      })
    };

    return this.http.get<User>(this.url + '/' + id, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Service: FindById Method');
      })
    );
  }


}
