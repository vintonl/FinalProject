import { Reservation } from './reservation';

export class ReviewOfGear {

  reviewOfGearId: number;
  rating: number;
  reviewOfGear: string;
  reservation: Reservation;

  constructor( reviewOfGearId?: number, rating?: number, reviewOfGear?: string, reservation?: Reservation) {
    this.reviewOfGearId = reviewOfGearId;
    this.rating = rating;
    this.reviewOfGear = reviewOfGear;
    this.reservation = reservation;
  }
}
