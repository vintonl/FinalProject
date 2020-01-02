import { Reservation } from './reservation';

export class ReviewOfShopper {

  id: number;
  rating: number;
  review: string;
  reservation: Reservation;
  active: boolean;

  constructor( id?: number, rating?: number, review?: string, reservation?: Reservation, active?: boolean) {
    this.id = id;
    this.rating = rating;
    this.review = review;
    this.reservation = reservation;
    this.active = active;
  }
}
