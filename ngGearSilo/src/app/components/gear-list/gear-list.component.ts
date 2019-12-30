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

  // Categories

  categories = [
    'All',
    'Mountain Biking',
    'Skating',
    'Surf',
    'Hiking',
    'Biking',
    'Kayaking',
    'Water Sports',
    'Rock Climbing',
    'Skiing ',
    'Snowboarding',
    'Freefalling',
    'Wakeboarding',
    'Snow',
    'Water',
    'Mountain',
    'Sky',
    'Winter'
  ];
  selectedType = 'All';


  // Constructor
  constructor(private gearSrv: GearService, private resService: ReservationService,
    // tslint:disable-next-line: align
    private router: Router, private authService:
      AuthService, private revOfLenderService: ReviewOfLenderService, private mapService: MapService, private userSVC: UserService) { }

  ngOnInit() {
    this.hideSearchResult = true;
    this.selected = null;
    this.searchedGear = [];
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

              // this.distanceFromGear = 0;
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

    console.log(this.keyword)

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

  getLocation(item: Gear) {
    console.log("inside get location");

    this.mapService.getAll(item).subscribe(
      (goodRequest) => {
        this.location = goodRequest;
        console.log("logging a good request by gear ");

        item.lat = this.location.results[0].geometry.location.lat;
        item.long = this.location.results[0].geometry.location.lng;



        this.getDistance(this.lat, this.long, item);
        console.log(item.lat);
        console.log(item.long);

      },
      (bad) => {
        console.log('Error in Gear Comp - fetching map geocode from Map Service ');
        console.log(bad);
      }
    );
  }

  getUserLocation(add: Address) {
    console.log("inside get location");

    this.mapService.getUserAddress(add).subscribe(
      (goodRequest) => {
        this.location = goodRequest;
        console.log("logging a good request b ADDRESS");
        console.log(goodRequest);

        this.lat2 = this.location.results[0].geometry.location.lat;
        this.long2 = this.location.results[0].geometry.location.lng;
      },
      (bad) => {
        console.log('Error in Gear Comp - fetching map geocode from Map Service ');
        console.log(bad);
      }
    );
  }

  loadUser() {
    console.log("load user");
    this.authService.getUserByUsername(this.authService.getLoggedInUsername()).subscribe(
      yes => {
        this.loggedInUser = yes;
        console.log("LOGGING LOGGED USER ADDRESS");
        console.log(this.loggedInUser.address);
        this.getUserLocation(this.loggedInUser.address);

      },
      no => {
        console.error('Error getting logged in user');
        console.error(no);
      }
    );
  }




  getDistance(lat, long, item) {
    // this.distanceFromGear = 0;

    console.log(item.id);
    console.log("**************" + item.lat + "   " + item.long);
    const lat1 = item.lat;
    const long1 = item.long;


    // const lat2 = 39.536421;
    // const long2 = -104.865641;

    let R = 6378137; // Earth’s mean radius in meter
    let dLat = rad(this.lat2 - item.lat);

    console.log(dLat);
    let dLong = rad(this.long2 - item.long);
    console.log(dLong);

    let a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
      Math.cos(rad(item.lat)) * Math.cos(rad(this.lat2)) *
      Math.sin(dLong / 2) * Math.sin(dLong / 2);

    console.log(a);
    let c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    let d = R * c;

    console.log("logging D")
    console.log(d);

    this.distanceFromGear = (d * 0.00062137);
    item.distance = this.distanceFromGear;

    console.log("logging distance")
    console.log(this.distanceFromGear);

    return d; // returns the distance in meter
  };
};

let rad = function (x) {
  return x * Math.PI / 180;




}



