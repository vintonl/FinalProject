import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { Gear } from './../../models/gear';
import { GearService } from './../../services/gear.service';
import { Component, OnInit } from '@angular/core';
import { isNgTemplate } from '@angular/compiler';
import { userInfo } from 'os';

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



  constructor(private gearSrv: GearService, private router: Router, private authService: AuthService) { }

  ngOnInit() {
    const cred = this.authService.getCredentials();

    if (cred === null) {
      this.router.navigateByUrl('/login');

    }

    this.loadGear();
  }

  loadGear() {
    this.gearSrv.index().subscribe(
      (aGoodThingHappened) => {
        this.gearList = aGoodThingHappened;
        this.gearList.forEach(gear => {
          if (gear.user.imageUrl === null || gear.user.imageUrl === undefined || gear.user.imageUrl.length < 10) {
            gear.user.imageUrl = 'https://i.imgur.com/zVdNnTx.png';
            this.currentRate = gear.user.id;
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

}
