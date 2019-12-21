import { Router } from '@angular/router';
import { Gear } from './../../models/gear';
import { GearService } from './../../services/gear.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-gear-list',
  templateUrl: './gear-list.component.html',
  styleUrls: ['./gear-list.component.css']
})
export class GearListComponent implements OnInit {
  gearList: Gear[] = [];
  selected: Gear;


  constructor(private gearSrv: GearService, private router: Router) { }

  ngOnInit() {
    this.loadGear();
  }

  loadGear() {
    // this.clearSearch();
    this.gearSrv.index().subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.gearList = aGoodThingHappened;
      },
      (didntWork) => {
        console.log(didntWork);
      }
    );
  }

  displayGearItem(gear: Gear) {
    console.log(gear);
    this.selected = gear;
    this.gearSrv.selected = gear;
    console.log(this.selected);
  }

  startReservation() {
    this.router.navigate(['/reservation']);
  }

  getSelected() {
    console.log(this.selected);
    return this.selected;
  }
}
