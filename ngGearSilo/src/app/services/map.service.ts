import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { Gear } from '../models/gear';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MapService {

  private key = 'AIzaSyDn9n7EjBjBQH96FwmLXV37zuw5IN5PRII';


  private gmapsUrl: string = 'https://maps.googleapis.com/maps/api/geocode/json?address='
  private url: string = 'https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key='

  // 1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=YOUR_API_KEY'


  constructor(private http: HttpClient) { };

  getAll(item: Gear) {
    console.log("in service map")
    // console.log(item.user.address.address.replace(' ', +));
    return this.http.get(this.gmapsUrl  + item.user.address.city + ',+' + item.user.address.state + '&key=' + this.key).pipe(map((response: Response) => response));
  }
}
