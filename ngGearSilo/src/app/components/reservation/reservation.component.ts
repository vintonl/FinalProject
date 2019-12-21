import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { GearListComponent } from './../gear-list/gear-list.component';
import { Component, OnInit } from '@angular/core';
import { Gear } from 'src/app/models/gear';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  selected: Gear = null;

  constructor(private gearlistComp: GearListComponent, private router: Router, private authService: AuthService) { }

  ngOnInit() {
    if (this.authService.getCredentials() === null) {
      this.router.navigateByUrl('/login');
    }

  }

  // loadSelected() {
  //   this.selected = this.gearlistComp.selected;
  // }


}
