import { GearService } from './../../services/gear.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Gear } from 'src/app/models/gear';
import { AuthService } from 'src/app/services/auth.service';
import { ProfileService } from 'src/app/services/profile.service';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  gearList: Gear[] = [];
  public isCollapsed: boolean[] = [];
  newGear: Gear = new Gear();
  updatedGear: Gear = new Gear();
  public selecteditem: Gear=  new Gear;



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
    this.gearList = [];

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

  deleteGear(id: number) {

    console.log("in delete gear profile comp");
    console.log(id);

    this.gearSrv.destroy(id).subscribe(
      (good) => {
        console.log(good);


      },
      (bad) => {
        console.log("error " + bad);
      }
    );
    this.ngOnInit();
  }

  addGear() {
    console.log("in add gear " + this.newGear.active);
    this.newGear.active = true;
    this.newGear.available = true;
    console.log("in add gear " + this.newGear.active);



    this.gearSrv.create(this.newGear).subscribe(
      newGear => {
        this.loadGear();
        this.newGear = new Gear();
      },
      err => console.log('Observer got an error: ' + err)
    );
  }


  onClick(item: any, lgModal: any) {

    this.selecteditem = item;

    lgModal.show();

    //  console.log(this.selecteditem); // print in console

  }

  updateGear() {

    console.log("in profile comp update + gear id" + this.updatedGear.id + "  " + this.updatedGear.description);

    this.gearSrv.update(this.updatedGear).subscribe(
      data => {
        this.updatedGear = data;
        this.updatedGear = null;

      },
      err => console.log('Update got an error: ' + err)
    );

  }

}


