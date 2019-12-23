import { Reservation } from './../../models/reservation';
import { ReservationService } from './../../services/reservation.service';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { Gear } from './../../models/gear';
import { GearService } from './../../services/gear.service';
import { Component, OnInit } from '@angular/core';
import { isNgTemplate } from '@angular/compiler';
import { User } from 'src/app/models/user';

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
  loggedInUser: User = new User;



  constructor(private gearSrv: GearService, private resService: ReservationService,
              private router: Router, private authService: AuthService) { }

  ngOnInit() {
    const cred = this.authService.getCredentials();

    if (cred === null) {
      this.router.navigateByUrl('/login');

    }

    this.loadGear();
    this.loadReseravtions();
  }

  loadGear() {
    this.gearSrv.index().subscribe(
      (aGoodThingHappened) => {
        this.gearList = aGoodThingHappened;
        this.gearList.forEach(gear => {
          if (gear.user.imageUrl === null || gear.user.imageUrl === undefined || gear.user.imageUrl.length < 10) {
            gear.user.imageUrl = 'https://i.imgur.com/zVdNnTx.png';
          }

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
    document.getElementById("myForm").style.display = "block";
  }

   closeForm() {
    document.getElementById("myForm").style.display = "none";
  }

 // LOAD RESERVATIONS FOR USER
 loadReseravtions() {
  this.resList = [];
  this.authService.getUserByUsername(this.authService.getLoggedInUsername()).subscribe(
    yes => {
      this.loggedInUser = yes;
      console.log('Got logged in user:');
      console.log(this.loggedInUser);
      this.resService.index().subscribe(
        (aGoodThingHappened) => {
          console.log("loggin a good thing happend");
          console.log(aGoodThingHappened);
          aGoodThingHappened.forEach(res => {
            this.currentRate = res.lenderReview.rating;
            console.log('in load res from profile ts');
            console.log("logging all id in res");
            console.log(aGoodThingHappened.length);
            this.resList.push(res);
            // this.lenderRating();
            // if (res.gearId.user.id === this.loggedInUser.id) {
            console.log(res);
            console.log("in the for each for res");
            console.log(res.gearId.user.id);
            console.log(this.loggedInUser.id);
            // this.rating = res.lenderReview.rating;
            console.log('about to be in rating sum');
            // this.lenderRating();
            // this.loggedInUser = e.user;
            // }
          });
        },
        (didntWork) => {
          console.log('in load res from profile ts didnt work');
          console.log(didntWork);
        }
      );
    },
    no => {
      console.error('Error laoding res in user');
      console.error(no);
    }
  );
  console.log(this.loggedInUser);
}

}

