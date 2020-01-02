import { Reservation } from 'src/app/models/reservation';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { Injectable } from '@angular/core';
import { ReviewOfGear } from '../models/review-of-gear';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ReviewOfShopperService {
  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api/';

  constructor(private http: HttpClient, private authService: AuthService) { }



  createGearReview(newGearReview, user) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ` + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      })
    };

    return this.http.post<ReviewOfGear>(this.url + 'users/' + user.id + '/reservation/'
      + newGearReview.reservation.id + '/reviews/gearreviews', newGearReview, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('ReviewOfShopperService.create(): Error creating new gear review');
        })
      );
  }


}
