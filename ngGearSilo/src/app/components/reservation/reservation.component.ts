import { Reservation } from './../../models/reservation';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { GearListComponent } from './../gear-list/gear-list.component';
import { Component, OnInit } from '@angular/core';
import { Gear } from 'src/app/models/gear';
import { GearService } from 'src/app/services/gear.service';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  selected = null;
  newRes: Reservation = null;

  constructor(private gearSrv: GearService, private gearlistComp: GearListComponent,
    // tslint:disable-next-line: align
    private router: Router, private authService: AuthService) { }

  ngOnInit() {
    if (this.authService.getCredentials() === null) {
      this.router.navigateByUrl('/login');
    }
    this.loadSelected();
  }

  loadSelected() {
    this.selected = this.gearSrv.selected;
    console.log(this.selected);
  }

  makeReservation() {

  }

}
