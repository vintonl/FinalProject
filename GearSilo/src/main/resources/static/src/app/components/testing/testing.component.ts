import { GearService } from 'src/app/services/gear.service';
import { UserService } from 'src/app/services/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Gear } from 'src/app/models/gear';



@Component({
  selector: 'app-testing',
  templateUrl: './testing.component.html',
  styleUrls: ['./testing.component.css']
})
export class TestingComponent implements OnInit {
  users: User[] = [];
  selectedUser: User = null;
  user: User = null;
  updateUser: User = null;
  disableUser: User = null;

  gearList: Gear[] = [];
  gear: Gear = null;
  selectedGear: Gear = null;

  constructor(private userSvc: UserService, private gearSvc: GearService) { }

  ngOnInit() {
    this.loadUsers();
  }

  // User List

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

  // Gear List



}
