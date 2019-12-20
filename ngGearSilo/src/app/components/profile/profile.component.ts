import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { GearService } from 'src/app/services/gear.service';
import { Gear } from 'src/app/models/gear';
import { AuthService } from 'src/app/services/auth.service';
import { ProfileService } from 'src/app/services/profile.service';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  gearList: Gear[] = [];


  constructor(private gearSrv: GearService, private router: Router, private authService: AuthService, private userService: UserService) { }

  loggedInUser: User = new User();
  myGear: Gear[] = [];


  ngOnInit() {
    const cred = this.authService.getCredentials();

    if (cred != null) {
      this.loadGear();
    }
    this.router.navigateByUrl('/login');
  }

  loadGear() {
    // this.clearSearch();
    const allgear: [] = [];

    this.gearSrv.index().subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);

        aGoodThingHappened.forEach(e => {

          if (e.user.id === e.id) {
            this.gearList.push(e);
            this.loggedInUser = e.user;
          }

        });

      },
      (didntWork) => {
        console.log(didntWork);
      }
    );
  }

  // GetLoggedInUserGear() {

  //   const username = this.authService.getUsername();

  //   console.log("in get logged in usergear profile ts " + username);

  //   this.gearSrv.getGearByUserName(username).subscribe(
  //     data => {
  //       this.myGear = data;
  //       console.log("success in get user gear profile componoent " + this.myGear);

  //     },
  //     err => {
  //       console.error(err);
  //       console.log("error in get user gear")
  //     }
  //   );

  // }
}


