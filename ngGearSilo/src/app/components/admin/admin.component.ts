import { GearService } from 'src/app/services/gear.service';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Gear } from 'src/app/models/gear';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  users: User[] = [];
  selectedUser: User = null;
  user: User = null;
  updateUser: User = null;
  disableUser: User = null;

  gearList: Gear[] = [];
  gear: Gear = null;
  selectedGear: Gear = null;

  constructor(private userSvc: UserService, private gearSvc: GearService) {}

  ngOnInit() {
    this.loadUsers();
    this.loadGear();
  }
  public loadUsers() {

    const userList: [] = [];

    this.userSvc.index().subscribe(
      good => {
        console.log(good);
        this.users = good;
      },
      bad => {
        console.log(bad);
      }
    );
  }

  public countUsers() {
    return this.users.length;
  }

  public setUpdateExpense() {
    this.updateUser = Object.assign({}, this.selectedUser);
  }

  public updatedUser(user: User) {
    this.userSvc.update(user).subscribe(
      uData => {
        this.loadUsers();
        this.selectedUser = null;
        this.updatedUser = null;
      },
      uErr => {
        this.loadUsers();
        console.error('updatedExpense: Error');
        console.error(uErr);
      }
    );
  }

  public loadGear() {
    // this.clearSearch();
    this.gearSvc.index().subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.gearList = aGoodThingHappened;
      },
      (didntWork) => {
        console.log(didntWork);
      }
    );
  }

  public displayGearItem(gear: Gear) {
    this.selectedGear = gear;
  }
}
