import { Address } from './../../models/address';
import { ReviewOfLenderService } from './../../services/review-of-lender.service';
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
import { count } from 'rxjs/operators';
import { MapService } from 'src/app/services/map.service';
import { fadeInContent } from '@angular/material';
import { UserService } from 'src/app/services/user.service';


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
  location;
  selectedGear: Gear;
  selectedGearReviews: ReviewOfGear[];
  lat = null;
  long = null;
  distanceFromGear;
  lat2;
  long2;
  searchDistance = 4000;
  min = 0;
  max = 2000;

  // Categories

  categories = [
    'All',
    'Mountain Biking',
    'Skating',
    'Surf',
    'Hiking',
    'Biking',
    'Water Sports',
    'Rock Climbing',
    'Skiing',
    'Snowboarding',
    'Freefalling',
    'Snow',
    'Winter',
    'Sports'
  ];
  selectedType = 'All';


  // Constructor
  constructor(private gearSrv: GearService, private resService: ReservationService,
    // tslint:disable-next-line: align
    private router: Router, private authService:
      // tslint:disable-next-line: align
      AuthService, private revOfLenderService: ReviewOfLenderService, private mapService: MapService, private userSVC: UserService) { }

  ngOnInit() {
    this.hideSearchResult = true;
    this.selected = null;
    this.searchedGear = [];
    this.searchDistance = 2000;
    this.loadUser();
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
          this.revOfLenderService.loadGearOwnerReviews(gear.user).subscribe(
            (good) => {
              let ratingAvg = 0;
              // tslint:disable-next-line: no-shadowed-variable
              let count = 0;
              if (good != null) {
                // tslint:disable-next-line: prefer-for-of
                for (let index = 0; index < good.length; index++) {
                  if (good[index].rating != null) {
                    ratingAvg += good[index].rating;
                    count++;
                  }
                }
              }
              gear.user.userLenderRating = ratingAvg / count;

              this.getLocation(gear);
            },
            (bad) => {
              console.log('Error in GearListComponent.loadGear() loading reviews of lender');
              console.log(bad);
            }
          );
          gear.user.userLenderRating = 0;
        });
      },
      (didntWork) => {
        console.log(didntWork);
      }
    );
  }

  displayGearItem(gear: Gear) {
    this.selected = gear;
    this.getLocation(this.selected);
    this.gearSrv.selected = gear;
    this.searchedGear.length = 0;
    this.gearSrv.loadGearReviews().subscribe(
      (goodRequest) => {
        this.selectedGearReviews = goodRequest;
        this.getLocation(this.selected);
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
    console.log(this.keyword);


    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < this.gearList.length; i++) {
      this.getLocation(this.gearList[i]);
      if (this.gearList[i].name.toLowerCase().includes(this.keyword.toLowerCase())) {
        this.searchedGear.push(this.gearList[i]);
        continue;
      }
      if (this.gearList[i].description.toLowerCase().includes(this.keyword.toLowerCase())) {
        this.searchedGear.push(this.gearList[i]);
        continue;
      }
      // if (this.gearList[i].gearCondition.toLowerCase().includes(this.keyword.toLowerCase())) {
      //   this.searchedGear.push(this.gearList[i]);
      //   continue;
      // }
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
        continue;
      }
      if (this.gearList[i].distance < this.keyword) {
        this.searchedGear.push(this.gearList[i]);
      }
    }

    console.log("printing search results");
    console.log(this.searchedGear.length);

    this.hideSearchResult = false;

    this.keyword = null;
  }

  searchByDistance() {
    this.searchedGear = [];

    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < this.gearList.length; i++) {
      console.log(this.gearList[i].distance);
      const distanceNumber = +this.gearList[i].distance;

      if (distanceNumber <= this.searchDistance) {
        this.searchedGear.push(this.gearList[i]);
      }
    }

    this.hideSearchResult = false;

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
    this.resService.index().subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);

        this.resList = aGoodThingHappened;
        this.resList.forEach(res => {

          if (res.lenderReview.rating > 0 ) {

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

  }

  // GETS LOCATION OF EACH ITEM AND CALLS THE CALCULATE DISTANCE METHOD
  getLocation(item: Gear) {
    console.log('inside get location');
    // this.lat = null;
    // this.long = null;

    this.mapService.getAll(item).subscribe(
      (goodRequest) => {
        this.location = goodRequest;

        item.lat = this.location.results[0].geometry.location.lat;
        item.long = this.location.results[0].geometry.location.lng;
        this.getDistance(this.lat, this.long, item);
        this.lat = item.lat;
        this.long = item.long;

      },
      (bad) => {
        console.log('Error in Gear Comp - fetching map geocode from Map Service ');
        console.log(bad);
      }
    );
  }

  getUserLocation(add: Address) {
    console.log('inside get location');

    this.mapService.getUserAddress(add).subscribe(
      (goodRequest) => {
        this.location = goodRequest;

        this.lat2 = this.location.results[0].geometry.location.lat;
        this.long2 = this.location.results[0].geometry.location.lng;
      },
      (bad) => {
        console.log('Error in Gear Comp - fetching map geocode from Map Service ');
        console.log(bad);
      }
    );
  }

  // LOADS THR LOGGED IN USER AND GETS THEIR LOCATION
  loadUser() {
    this.authService.getUserByUsername(this.authService.getLoggedInUsername()).subscribe(
      yes => {
        this.loggedInUser = yes;
        this.getUserLocation(this.loggedInUser.address);

      },
      no => {
        console.error('Error getting logged in user');
        console.error(no);
      }
    );
  }



  // HAVERSINE FORMULA TO GET STRAIGHT_LINE DISTANCE
  getDistance(lat, long, item) {

    const R = 6378137; // Earth’s mean radius in meter
    const dLat = rad(this.lat2 - item.lat);

    const dLong = rad(this.long2 - item.long);

    const a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
      Math.cos(rad(item.lat)) * Math.cos(rad(this.lat2)) *
      Math.sin(dLong / 2) * Math.sin(dLong / 2);

    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    const d = R * c;


    this.distanceFromGear = (d * 0.00062137);
    item.distance = this.distanceFromGear;


  }
}

// tslint:disable-next-line: only-arrow-functions
const rad = function (x: number) {
  return x * Math.PI / 180;

};



