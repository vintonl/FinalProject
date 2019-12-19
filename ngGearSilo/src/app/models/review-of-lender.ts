import { Reservation } from './reservation';

export class ReviewOfLender {

  reviewOfLenderId: number;
  rating: number;
  reviewOfShopper: string;
  reservation: Reservation;

  constructor( reviewOfLenderId?: number, rating?: number, reviewOfShopper?: string, reservation?: Reservation) {
    this.reviewOfLenderId = reviewOfLenderId;
    this.rating = rating;
    this.reviewOfShopper = reviewOfShopper;
    this.reservation = reservation;
  }
}
