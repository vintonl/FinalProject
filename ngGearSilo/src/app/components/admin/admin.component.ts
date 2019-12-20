import { UserService } from "./../../services/user.service";
import { Component, OnInit } from "@angular/core";
import { User } from "src/app/models/user";

@Component({
  selector: "app-admin",
  templateUrl: "./admin.component.html",
  styleUrls: ["./admin.component.css"]
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

  loadUsers() {
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
}
