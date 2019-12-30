import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';
import { ReviewOfGear } from '../models/review-of-gear';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class ReviewOfLenderService {
  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api/users/';


  constructor(private http: HttpClient, private router: Router, private authService: AuthService) { }

  loadGearOwnerReviews(user: User) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ` + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.get<ReviewOfGear[]>(this.url + user.id + '/reviews/lenderreviews', httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('GearService.loadGearReviews(): Error');
      })
    );
  }
}
