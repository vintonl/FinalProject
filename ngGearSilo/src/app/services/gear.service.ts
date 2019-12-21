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
  selected: Gear;
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

  getGearByUserName(username: any) {
    console.log('in get gear by user name gear service');
    console.error(username);

    if (localStorage.length === 0) {
      this.router.navigateByUrl('/login');
    }
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ` + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.get<Gear[]>(this.url + '/users/' + username, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error gear service - getGearByUser');
      })
    );
  }

  destroy(id: number) {
    console.log("id for delete");
    console.log(id);

    if (!this.authService.checkLogin()) {
      return null;
    }

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ` + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.delete<Gear>(this.url + '/' + id, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error deleting gear ');
      })
    );
  }

  create(newGear: Gear) {



    console.log('in create  - gear service' + newGear.name);
    // newGear.completed = false;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ` + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.post<Gear>(this.url + '/users', newGear, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('GearService.create(): Error');
      })
    );
  }

  update(updatedgear: Gear) {


    // if (todo.completed) {
    //   todo.completeDate = this.datePipe.transform(Date.now(), 'shortDate');
    // } else {
    //   todo.completeDate = '';
    // }
    console.log("in update service " + updatedgear.active + " " + updatedgear.id);

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ` + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.put<Gear>(this.url + '/users/' + updatedgear.id, updatedgear, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('GearService.update(): Error');
      })
    );
  }


}
