import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';

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

  constructor(private userSvc: UserService) {}

  ngOnInit() {
    this.loadUsers();
  }
  public loadUsers() {
    // this.userSvc
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
}
