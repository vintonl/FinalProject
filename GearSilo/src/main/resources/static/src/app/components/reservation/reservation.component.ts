import { ReservationService } from './../../services/reservation.service';
import { Reservation } from './../../models/reservation';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { GearService } from 'src/app/services/gear.service';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {
  selected = null;
  newRes: Reservation = new Reservation();
  badRes = false;

  constructor(private gearSrv: GearService, private resSrv: ReservationService,
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
  }

  createReservation() {
    this.newRes.gearId = this.selected;
    this.newRes.approved = false;
    this.newRes.completed = false;

    this.resSrv.create(this.newRes).subscribe(
      created => {
        this.router.navigateByUrl('/users');
      },
      err => {
        this.badRes = true;
      }
    );
  }

  toGears() {
    this.router.navigateByUrl('/gears');
  }

}
