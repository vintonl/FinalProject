import { User } from './user';
import { Reservation } from './reservation';
import { ThrowStmt } from '@angular/compiler';

export class ReservationMessage {

  id: number;
  message: string;
  messageDate: Date;
  shopperUserId: User;
  reservation: Reservation;

  constructor( id?: number, message?: string, messageDate?: Date, shopperUserId?: User, reservation?: Reservation) {
    this.id = id;
    this.message = message;
    this.messageDate = messageDate;
    this.shopperUserId = shopperUserId;
    this.reservation = reservation;
  }
}

