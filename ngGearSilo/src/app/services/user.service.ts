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
  users: User[] = [];
  newUser = new User();

  private baseUrl = '/GearSilo/'; // Production
  private url = environment.baseUrl + 'api/users';

  constructor(
    private http: HttpClient,
    private router: Router,
    private authService: AuthService
  ) {}

  index() {
    const httpOptions = {
      headers: new HttpHeaders({
        // 'X-Requested-With': 'XMLHttpRequest',
        'Content-Type': 'application/json'
      })
    };
    return this.http.get<User[]>(this.url, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Service: Index Method');
      })
    );
  }


}
