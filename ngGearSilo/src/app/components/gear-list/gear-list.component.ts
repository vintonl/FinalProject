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


  constructor(private gearSrv: GearService) { }

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

}
