import { ReviewOfGear } from './../../models/review-of-gear';
import { ReviewOfLender } from './../../models/review-of-lender';
import { Reservation } from './../../models/reservation';
import { ReservationService } from './../../services/reservation.service';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { Gear } from './../../models/gear';
import { GearService } from './../../services/gear.service';
import { Component, OnInit } from '@angular/core';
import { isNgTemplate } from '@angular/compiler';
import { User } from 'src/app/models/user';
import { Category } from 'src/app/models/category';


@Component({
  selector: 'app-gear-list',
  templateUrl: './gear-list.component.html',
  styleUrls: ['./gear-list.component.css']
})
export class GearListComponent implements OnInit {
  gearList: Gear[] = [];
  selected: Gear;
  keyword: string = null;
  list = false;
  searchResult = false;
  searchedGear: Gear[] = [];
  hideSearchResult = true;
  currentRate = null;
  resList: Reservation[] = [];
  loggedInUser: User = new User();
  rating = 0;
  averageRating = 0;
  selectedGear: Gear;
  selectedGearReviews: ReviewOfGear[];

  // Categories

  categories = [
    'all',
    'Mountain Biking',
    'Skating',
    'Surf',
    'Hiking',
    'Kayaking',
    'Water Sports',
    'Rock Climbing',
    'Skiing',
    'Snowboarding',
    'Freefalling',
    'Wakeboarding',
    'Snow',
    'Water',
    'Mountain',
    'Sky',
    'Biking',
    'Winter'
  ];
  selectedType = 'all';


  // Constructor


  constructor(private gearSrv: GearService, private resService: ReservationService,
    // tslint:disable-next-line: align
    private router: Router, private authService: AuthService) { }

  ngOnInit() {
    this.hideSearchResult = true;
    this.selected = null;
    this.searchedGear = [];
    this.loadGear();
    this.loadReseravtions();
  }

  loadGear() {
    this.gearSrv.index().subscribe(
      (aGoodThingHappened) => {
        console.log('in a aGoodThingHappened Gear');
        console.log(aGoodThingHappened);
        this.gearList = aGoodThingHappened;
        this.gearList.forEach(gear => {
          if (gear.user.imageUrl === null || gear.user.imageUrl === undefined || gear.user.imageUrl.length < 10) {
            gear.user.imageUrl = 'https://i.imgur.com/zVdNnTx.png';
          }

          gear.user.userLenderRating = gear.user.id;
        });
      },
      (didntWork) => {
        console.log(didntWork);
      }
    );
  }

  displayGearItem(gear: Gear) {
    this.selected = gear;
    this.gearSrv.selected = gear;
    this.gearSrv.loadGearReviews().subscribe(
      (goodRequest) => {
        this.selectedGearReviews = goodRequest;
      },
      (bad) => {
        console.log('Error in GearListComponent.displayGearItem() loading gear reviews');
        console.log(bad);
      }
    );
  }

  startReservation() {
    this.router.navigate(['/reservation']);
  }

  search() {
    this.searchedGear = [];

    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < this.gearList.length; i++) {
      if (this.gearList[i].name.toLowerCase().includes(this.keyword.toLowerCase())) {
        this.searchedGear.push(this.gearList[i]);
        continue;
      }
      if (this.gearList[i].description.toLowerCase().includes(this.keyword.toLowerCase())) {
        this.searchedGear.push(this.gearList[i]);
        continue;
      }
      if (this.gearList[i].gearCondition.toLowerCase().includes(this.keyword.toLowerCase())) {
        this.searchedGear.push(this.gearList[i]);
        continue;
      }
      if (this.gearList[i].user.firstName.toLowerCase().includes(this.keyword.toLowerCase())) {
        this.searchedGear.push(this.gearList[i]);
        continue;
      }
      if (this.gearList[i].user.lastName.toLowerCase().includes(this.keyword.toLowerCase())) {
        this.searchedGear.push(this.gearList[i]);
        continue;
      }
      if (this.gearList[i].user.address.city.toLowerCase().includes(this.keyword.toLowerCase())) {
        this.searchedGear.push(this.gearList[i]);
        continue;
      }
      if (this.gearList[i].user.address.state.toLowerCase().includes(this.keyword.toLowerCase())) {
        this.searchedGear.push(this.gearList[i]);
      }
    }
    this.hideSearchResult = false;

    this.keyword = null;
  }

  openForm() {
    document.getElementById('myForm').style.display = 'block';
  }

  closeForm() {
    document.getElementById('myForm').style.display = 'none';
  }

  // LOAD RESERVATIONS FOR USER
  loadReseravtions() {
    this.resList = [];
    // this.authService.getUserByUsername(this.authService.getLoggedInUsername()).subscribe(
    //   yes => {
    //     this.loggedInUser = yes;
    //     console.log('Got logged in user:');
    //     console.log(this.loggedInUser);

    this.resService.index().subscribe(
      (aGoodThingHappened) => {

        console.log('in a aGoodThingHappened REs');
        console.log(aGoodThingHappened);

        this.resList = aGoodThingHappened;

        console.log(this.resList);
        console.log(this.resList.length);
        console.log(this.resList.values);
        console.log('+++++++++++++++++====');


        this.resList.forEach(res => {
          console.log(res);
          console.log(res.lenderReview.rating);

          if (res.lenderReview.rating > 0) {



            this.rating = res.lenderReview.rating;

            this.averageRating = this.rating;
          }



        });
      },
      (didntWork) => {
        console.log('in load res from profile ts didnt work');
        console.log(didntWork);
      }
    );
    //     },
    //     no => {
    //       console.error('Error laoding res in user');
    //       console.error(no);
    //     }
    //   );
    //   console.log(this.loggedInUser);
  }



}

