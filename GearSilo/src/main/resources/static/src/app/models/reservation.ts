import { ReservationMessage } from './reservation-message';
import { User } from 'src/app/models/user';
import { Gear } from 'src/app/models/gear';
import { ReviewOfLender } from './review-of-lender';
import { ReviewOfGear } from './review-of-gear';
import { ReviewOfShopper } from './review-of-shopper';

// Java Entity:
// private int id;
// 	private Date openDate;
// 	private Date closeDate;
// 	private Gear gearId;
// 	private ReviewOfLender lenderReview;
// 	private ReviewOfGear gearReview;
// 	private ReviewOfShopper shopperReview;
// 	private boolean completed;
// 	private User userShopper;
// 	private Date createdAt;
// 	private Date updatedAt;
// 	private boolean approved;

export class Reservation {
  id: number;
  openDate: Date;
  closeDate: Date;
  gearId: Gear;
  lenderReview: ReviewOfLender;
  gearReview: ReviewOfGear;
  shopperReview: ReviewOfShopper;
  completed: boolean;
  userShopper: User;
  createdAt: Date;
  updatedAt: Date;
  approved: boolean;
  reservationMessage: ReservationMessage;

  constructor(
    id?: number,
    openDate?: Date,
    closeDate?: Date,
    gearId?: Gear,
    lenderReview?: ReviewOfLender,
    gearReview?: ReviewOfGear,
    shopperReview?: ReviewOfShopper,
    completed?: boolean,
    userShopper?: User,
    createdAt?: Date,
    updatedAt?: Date,
    approved?: boolean,
    reservationMessage?: ReservationMessage
  ) {
    this.id = id;
    this.openDate = openDate;
    this.closeDate = closeDate;
    this.gearId = gearId;
    this.lenderReview = lenderReview;
    this.gearReview = gearReview;
    this.shopperReview = shopperReview;
    this.completed = completed;
    this.userShopper = userShopper;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.approved = approved;
    this.reservationMessage = reservationMessage;
  }
}
