import { Gear } from './../models/gear';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class GearService {
  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api/gears';

  constructor(private http: HttpClient, private router: Router, private authService: AuthService) { }

  index() {

    return this.http.get<Gear[]>(this.url)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('GearService.index() Error');
        })
      );
  }





}
