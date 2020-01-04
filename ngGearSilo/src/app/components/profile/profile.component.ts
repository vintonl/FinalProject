import { ReservationMessageService } from './../../services/reservation-message.service';
import { ReservationMessage } from './../../models/reservation-message';
import { ReviewOfLender } from './../../models/review-of-lender';
import { ReviewOfShopperService } from './../../services/review-of-shopper.service';
import { ReservationService } from './../../services/reservation.service';
import { GearService } from './../../services/gear.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Gear } from 'src/app/models/gear';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { Observable } from 'rxjs';
import { Reservation } from 'src/app/models/reservation';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {


  // F I E L D S
  gearList: Gear[] = [];
  public isCollapsed: boolean[] = [];
  newGear: Gear = new Gear();

  updatedGear: Gear = new Gear();
  updatedRes: Reservation = new Reservation();

  public selecteditem: Gear = new Gear;
  public selectedRes: Reservation = new Reservation;

  editedUser: User = new User();
  reservations: Reservation = new Reservation();
  loggedInUser: User = new User();
  myGear: Gear[] = [];
  myReservations: Reservation[] = [];
  shopperReservations: Reservation[] = [];
  myRes: Reservation;
  rating: number;
  deleteId: number;
  needApprovedRes = 0;
  needsApprovedRes: Reservation[] = [];
  needCompletedRes = 0;
  needsCompletedRes: Reservation[] = [];
  reservationStatus;
  userneedsCompletedRes: Reservation[] = [];
  userneedsCompletedResNum = 0;
  marked = false;
  theCheckbox = false;
  resMessage: ReservationMessage = new ReservationMessage();
  resMessages: ReservationMessage[] = [];
  message: string;


  // C O N S T R U C T O R
  constructor(private gearSrv: GearService,
    private resMessageSrv: ReservationMessageService,
    private router: Router, private authService: AuthService,
    private userService: UserService,
    private resService: ReservationService,
    private reviewOfShopperSvc: ReviewOfShopperService) { }



  ngOnInit() {
    const cred = this.authService.getCredentials();

    if (cred === null) {
      this.router.navigateByUrl('/login');
    }

    // reload page once to check if admin is logged in
    if (!localStorage.getItem('foo')) {
      localStorage.setItem('foo', 'no reload');
      location.reload();
    } else {
      localStorage.removeItem('foo');
    }

    this.loadGear();
    this.loadReseravtions();
  }

  reservationMessages() {
    this.myReservations.forEach(res => {
      this.message = res.reservationMessage.message;
    });
  }

  // LOAD THE GEAR
  loadGear() {
    this.gearList = [];

    this.authService.getUserByUsername(this.authService.getLoggedInUsername()).subscribe(
      yes => {
        this.loggedInUser = yes;
        console.log('Got logged in user:');
        console.log(this.loggedInUser);


        this.gearSrv.index().subscribe(
          (aGoodThingHappened) => {
            console.log(aGoodThingHappened);

            aGoodThingHappened.forEach(gear => {

              if (gear.user.id === this.loggedInUser.id) {
                this.gearList.push(gear);
                this.checkImageURl();
              }
            });
          },
          (didntWork) => {
            console.log(didntWork);
          }
        );
      },
      no => {
        console.error('Error getting logged in user');
        console.error(no);
      }
    );
  }


  // DELETE GEAR
  deleteGear() {

    console.log('in delete gear ');
    console.log(this.deleteId);

    this.gearSrv.destroy(this.deleteId).subscribe(
      (good) => {
        this.ngOnInit();
        console.log(good);
        this.deleteId = null;
      },
      (bad) => {
        console.log('error ' + bad);
        this.deleteId = null;
      }
    );
  }

  onClickDelete(itemId: number) {
    console.log('in delete click');
    console.log(itemId);

    this.deleteId = itemId;

  }

  // ADD GEAR
  addGear() {
    this.newGear.active = true;
    this.newGear.available = true;
    if (this.newGear.imageUrl === null || this.newGear.imageUrl === undefined) {
      this.newGear.imageUrl = 'https://i.imgur.com/gkIBm2x.png';
    }
    this.gearSrv.create(this.newGear).subscribe(
      newGear => {
        this.newGear = new Gear();
      },
      err => console.log('Observer got an error: ' + err)
    );
    this.ngOnInit();
    this.newGear = null;
  }

  onClick(item: any) {
    this.selecteditem = item;
    this.updatedGear = item;
  }

  onClickGearPopUp(item: any) {
    this.selecteditem = item;
  }

  // UPDATE THE GEAR
  updateGear() {
    this.updatedGear.id = this.selecteditem.id;
    this.updatedGear.active = true;

    if (this.updatedGear.name === null || this.updatedGear.name === undefined) {
      this.updatedGear.name = this.selecteditem.name;
    }
    if (this.updatedGear.description === null || this.updatedGear.description === undefined) {
      this.updatedGear.description = this.selecteditem.description;
    }
    if (this.updatedGear.imageUrl === null || this.updatedGear.imageUrl === undefined) {
      this.updatedGear.imageUrl = this.selecteditem.imageUrl;
    }
    if (this.updatedGear.price === null || this.updatedGear.price === undefined) {
      this.updatedGear.price = this.selecteditem.price;
    }
    if (this.updatedGear.gearCondition === null || this.updatedGear.gearCondition === undefined) {
      this.updatedGear.gearCondition = this.selecteditem.gearCondition;
    }
    if (this.updatedGear.available === null || this.updatedGear.available === undefined) {
      this.updatedGear.available = this.selecteditem.available;
    }
    this.selecteditem = null;
    this.gearSrv.update(this.updatedGear).subscribe(
      data => {
        location.reload();
      },
      err => console.log('Update got an error: ' + err));
  }

  // UPDATE USER
  updateUser() {
    this.editedUser.id = this.loggedInUser.id;
    this.editedUser.password = this.loggedInUser.password;
    this.editedUser.email = this.loggedInUser.email;
    this.editedUser.role = this.loggedInUser.role;

    if (this.editedUser.firstName === null || this.editedUser.firstName === undefined) {
      this.editedUser.firstName = this.loggedInUser.firstName;
    }
    if (this.editedUser.lastName === null || this.editedUser.lastName === undefined) {
      this.editedUser.lastName = this.loggedInUser.lastName;
    }
    if (this.editedUser.imageUrl === null || this.editedUser.imageUrl === undefined) {
      this.editedUser.imageUrl = this.loggedInUser.imageUrl;
    }
    if (this.editedUser.phone === null || this.editedUser.phone === undefined) {
      this.editedUser.phone = this.loggedInUser.phone;
    }

    this.userService.update(this.editedUser).subscribe(
      data => {
        this.editedUser = null;
        this.selecteditem = null;
      },
      err => console.log('Update got an error: ' + err)
    );
    this.editedUser = null;
    this.ngOnInit();
  }

  // LOAD ALL RESERVATIONS FOR USER
  loadReseravtions() {
    this.needApprovedRes = 0;
    this.myReservations = [];
    this.shopperReservations = [];
    this.userneedsCompletedRes = [];
    this.userneedsCompletedResNum = 0;

    // LOADING LENDER RESERVATIONS
    this.resService.index().subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        aGoodThingHappened.forEach(res => {
          this.myReservations.push(res);

          if (res.completed !== true) {
            this.needCompletedRes++;
            this.needsCompletedRes.push(res);
          }
          if (res.approved !== true) {
            this.needApprovedRes++;
            this.needsApprovedRes.push(res);
          }
          console.log(res);
        });
      },
      (didntWork) => {
        console.log('in load res from profile ts didnt work');
        console.log(didntWork);
      }
    );

    console.log('in load shopper res 0');

    // LOADING SHOPPER RESERVATIONS
    this.resService.indexShopperUser().subscribe(
      (aGoodThingHappened) => {
        console.log('in load shopper res 1');
        console.log(aGoodThingHappened);
        aGoodThingHappened.forEach(res => {



          if (res.completed === true && res.gearReview === null) {

            console.log(res);
            this.userneedsCompletedResNum++;
            this.userneedsCompletedRes.push(res);
            this.shopperReservations.push(res);
          }
        });
      },
      (didntWork) => {
        console.log('load shopper reservations didnt work');
        console.log(didntWork);
      }
    );
  }

  lenderRating() {
    this.myReservations.forEach(res => {
      this.rating = res.lenderReview.rating;
    });
  }

  checkImageURl() {

    if (this.loggedInUser.imageUrl.length < 10 || this.loggedInUser.imageUrl === null || this.loggedInUser.imageUrl === undefined) {
      this.loggedInUser.imageUrl = 'https://i.imgur.com/zVdNnTx.png';
    }

  }

  toggleVisibility() {
    console.log('in toggle');
    console.log(this.myRes.approved);

  }
  // UPDATE THE RESERVATION
  updateResCompleted(res) {
    if (this.updatedRes.createdAt === null || this.updatedRes.createdAt === undefined) {
      this.updatedRes.createdAt = this.selectedRes.createdAt;
    }
    if (this.updatedRes.openDate === null || this.updatedRes.openDate === undefined) {
      this.updatedRes.openDate = this.selectedRes.openDate;
    }
    if (this.updatedRes.closeDate === null || this.updatedRes.closeDate === undefined) {
      this.updatedRes.closeDate = this.selectedRes.closeDate;
    }
    if (this.updatedRes.updatedAt === null || this.updatedRes.updatedAt === undefined) {
      this.updatedRes.updatedAt = this.selectedRes.updatedAt;
    }
    if (this.updatedRes.approved === null || this.updatedRes.approved === undefined) {
      this.updatedRes.approved = this.selectedRes.approved;
    }
    if (this.updatedRes.gearId === null || this.updatedRes.gearId === undefined) {
      this.updatedRes.gearId = this.selectedRes.gearId;
    }
    if (this.updatedRes.userShopper === null || this.updatedRes.userShopper === undefined) {
      this.updatedRes.userShopper = this.selectedRes.userShopper;
    }
    this.updatedRes.id = this.selectedRes.id;
    this.updatedRes.completed = this.selectedRes.completed;
    if (this.updatedRes.completed === true) {
      this.updatedRes.completed = false;
    } else { this.updatedRes.completed = true; }
    this.selectedRes = null;
    this.resService.update(this.updatedRes).subscribe(
      data => {
        this.updatedRes = data;
        this.needCompletedRes = 0;
        // tslint:disable-next-line: prefer-for-of
        for (let i = 0; i < this.myReservations.length; i++) {
          if (this.myReservations[i].id === this.updatedRes.id) {
            this.myReservations[i].completed = this.updatedRes.completed;
          }
          if (!this.myReservations[i].completed) {
            this.needCompletedRes++;
          }
        }
        this.updatedRes = new Reservation();;
        this.selectedRes = null;
      },
      err => console.log('Update Res got an error: ' + err));
  }
  updateResApproval(res) {
    if (this.updatedRes.createdAt === null || this.updatedRes.createdAt === undefined) {
      this.updatedRes.createdAt = this.selectedRes.createdAt;
    }
    if (this.updatedRes.openDate === null || this.updatedRes.openDate === undefined) {
      this.updatedRes.openDate = this.selectedRes.openDate;
    }
    if (this.updatedRes.closeDate === null || this.updatedRes.closeDate === undefined) {
      this.updatedRes.closeDate = this.selectedRes.closeDate;
    }
    if (this.updatedRes.updatedAt === null || this.updatedRes.updatedAt === undefined) {
      this.updatedRes.updatedAt = this.selectedRes.updatedAt;
    }
    if (this.updatedRes.completed === null || this.updatedRes.completed === undefined) {
      this.updatedRes.completed = this.selectedRes.completed;
    }
    if (this.updatedRes.gearId === null || this.updatedRes.gearId === undefined) {
      this.updatedRes.gearId = this.selectedRes.gearId;
    }
    if (this.updatedRes.userShopper === null || this.updatedRes.userShopper === undefined) {
      this.updatedRes.userShopper = this.selectedRes.userShopper;
    }
    this.updatedRes.id = this.selectedRes.id;
    this.updatedRes.approved = this.selectedRes.approved;
    if (this.updatedRes.approved === true) {
      this.updatedRes.approved = false;
    } else { this.updatedRes.approved = true; }
    this.selectedRes = null;
    this.resService.update(this.updatedRes).subscribe(
      data => {
        this.updatedRes = data;
        this.needApprovedRes = 0;
        // tslint:disable-next-line: prefer-for-of
        for (let i = 0; i < this.myReservations.length; i++) {
          if (this.myReservations[i].id === this.updatedRes.id) {
            this.myReservations[i].approved = this.updatedRes.approved;
          }
          if (!this.myReservations[i].approved) {
            this.needApprovedRes++;
          }
        }
        this.updatedRes = new Reservation();;
        this.selectedRes = null;
      },
      err => console.log('Update Res got an error: ' + err));
  }

  onClickReservation(res: any) {
    this.selectedRes = res;
    this.updateResApproval(res);
  }
  onClickCompletedReservation(res: any) {
    this.selectedRes = res;
    this.updateResCompleted(res);

  }

  onClickReviewGear(res: any) {
    this.selectedRes = res;

  }

  createGearReview(gearReview: NgForm) {
    console.log(this.selectedRes.id);
    const newGearReview = {
      rating: gearReview.value.rating,
      review: gearReview.value.review,
      active: 'true',
      reservation: {
        id: this.selectedRes.id,
      }
    };

    let user = new User();

    this.authService.getUserByUsername(this.authService.getLoggedInUsername()).subscribe(
      good => {
        user = good;
        console.log(user);
        this.reviewOfShopperSvc.createGearReview(newGearReview, user).subscribe(
          next => {

            this.createLenderReview(gearReview, user);

            console.log('ReviewComponent.createGearReview(): review of gear created.');
            console.log(next);
          },
          error => {
            console.error('ReviewComponent.createGearReview(): error createGearReview.');
            console.log(error);
          }
        );
      },
      error => {
        console.log('ReviewOfShopperService.create() Error getting logged in user while creating gear review');
      }
    );
  }

  createLenderReview(gearReview: NgForm, user: User) {

    const newLenderReview = {
      rating: gearReview.value.lenderRating,
      review: "default review",
      active: 'true',
      reservation: {
        id: this.selectedRes.id
      }
    }

    this.reviewOfShopperSvc.createLenderReview(newLenderReview, user).subscribe(
      next => {

        console.log('ReviewComponent.createLenderReview(): review of lender created.');
        console.log(next);
      },
      error => {
        console.error('ReviewComponent.createLenderReview(): error createLenderReview.');
        console.log(error);
      }
    );

  };
}


