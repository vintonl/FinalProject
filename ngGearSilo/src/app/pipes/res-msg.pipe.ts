import { ReservationMessage } from './../models/reservation-message';
import { Pipe, PipeTransform } from '@angular/core';
import { User } from '../models/user';

@Pipe({
  name: 'resMsg'
})
export class ResMsgPipe implements PipeTransform {

  transform(resMsg: ReservationMessage[], thisUser: User, otherUser: User): ReservationMessage[] {
    const result: ReservationMessage[] = [];
    console.log('My ID ' + thisUser.id);
    console.log('Their ID ' + otherUser.id);

    resMsg.forEach((msg) => {
      console.log('Shopper ID ' + msg.shopperUserId.id);
      console.log('Shopper ' + msg.shopperUserId);
      console.log('Owner ID ' + msg.reservation.gearId.user.id);
      if((msg.reservation.gearId.user.id === thisUser.id) && (msg.reservation.userShopper.id === otherUser.id)) {
        result.push(msg);
        console.log('Adding msg');
      }
    });
    return result;
  }

}
