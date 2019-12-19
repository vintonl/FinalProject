import { Reservation } from './reservation';

export class ReviewOfShopper {

  reviewOfShopperId: number;
  rating: number;
  reviewOfShopper: string;
  reservation: Reservation;

  constructor( reviewOfShopperId?: number, rating?: number, reviewOfShopper?: string, reservation?: Reservation) {
    this.reviewOfShopperId = reviewOfShopperId;
    this.rating = rating;
    this.reviewOfShopper = reviewOfShopper;
    this.reservation = reservation;
  }
}
