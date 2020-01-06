import { Address } from './../models/address';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { Gear } from '../models/gear';

@Injectable({
  providedIn: 'root'
})
export class MapService {

  private key = 'AIzaSy';


  private gmapsUrl = 'https://maps.googleapis.com/maps/api/geocode/json?address='
  private url = 'https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key='



  constructor(private http: HttpClient) { };

  getAll(item: Gear) {
    // console.log('in service map by gear' + item.user.address.city)
    return this.http.get(this.gmapsUrl + item.user.address.city + ',+' + item.user.address.state + '&key=' + this.key).pipe(map(
      (response: Response) => response));
  }

  getUserAddress(add: Address) {
    // console.log('in service map by address' + add.city)
    return this.http.get(this.gmapsUrl + add.city + ',+' + add.state + '&key=' + this.key).pipe(map(
      (response: Response) => response));
  }
}
