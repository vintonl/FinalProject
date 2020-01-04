import { ReservationMessage } from "./../models/reservation-message";
import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Router } from "@angular/router";
import { AuthService } from "./auth.service";
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: "root"
})
export class ReservationMessageService {
  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + "api/messages";
  selected: ReservationMessage;
  constructor(
    private http: HttpClient,
    private router: Router,
    private authService: AuthService
  ) {}

  getMessageByUserName(username: any) {
    console.log("in get message by user name message service");
    console.error(username);

    if (localStorage.length === 0) {
      this.router.navigateByUrl("/login");
    }
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: `Basic ` + this.authService.getCredentials(),
        "X-Requested-With": "XMLHttpRequest"
      })
    };
    return this.http
      .get<ReservationMessage[]>(this.url + "/users/" + username, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("Error message service - getMessageByUser");
        })
      );
  }

  create(newMessage: ReservationMessage) {
    console.log('in create  - gear service' + newMessage.message);
    // newGear.completed = false;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ` + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.post<ReservationMessage>(this.url + '/users', newMessage, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('MessageService.create(): Error');
      })
    );
  }

  update(updatedMessage: ReservationMessage) {
    console.log('in update message service' + updatedMessage.message + " " + updatedMessage.id);
    // console.log(updatedRes.gearId.user.id);

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ` + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.put<ReservationMessage>(this.url + '/users', updatedMessage, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('MessageService.update(): Error');
      })
    );
  }

}
