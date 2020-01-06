import { ReservationMessage } from './../models/reservation-message';
import { Pipe, PipeTransform } from '@angular/core';
import { User } from '../models/user';

@Pipe({
  name: 'resMsg'
})
export class ResMsgPipe implements PipeTransform {

  transform(resMsg: ReservationMessage[], thisUser: User, otherUser: User): ReservationMessage[] {
    const result: ReservationMessage[] = [];
    resMsg.forEach((msg) => {
      if((msg.reservation.gearId.user.id === thisUser.id) && (msg.reservation.userShopper.id === otherUser.id)) {
        result.push(msg);
      }
      if((msg.reservation.gearId.user.id === otherUser.id) && (msg.reservation.userShopper.id === thisUser.id)) {
        result.push(msg);
      }
    });
    return result;
  }

}
