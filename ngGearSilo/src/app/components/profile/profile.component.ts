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

    if (cred === null) {
      this.router.navigateByUrl('/login');

    }

    this.loadGear();
  }

  loadGear() {
    // this.clearSearch(); const allgear: [] = [];
    // this.loggedInUser = this.authService.getUser();
    this.authService.getUserByUsername(this.authService.getLoggedInUsername()).subscribe(
      yes => {
        this.loggedInUser = yes;
        console.log('Got logged in user:');
        console.log(this.loggedInUser);


        this.gearSrv.index().subscribe(
          (aGoodThingHappened) => {
            console.log(aGoodThingHappened);

            aGoodThingHappened.forEach(gear => {

              if (gear.user.id === this.loggedInUser.id) {
                console.log("*************************get user id");
                console.log(gear.user.id);
                console.log(this.loggedInUser.id);
                this.gearList.push(gear);

                // this.loggedInUser = e.user;
              }

            });

          },
          (didntWork) => {
            console.log(didntWork);
          }
        );
      },
      no => {
        console.error('Error getting logged in user');
        console.error(no);


      }
    );

    console.log(this.loggedInUser);
    // this.loggedInUser = this.userService.getUserById();


  }


}


