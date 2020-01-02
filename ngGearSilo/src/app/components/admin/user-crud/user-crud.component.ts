import { UserService } from './../../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-user-crud',
  templateUrl: './user-crud.component.html',
  styleUrls: ['./user-crud.component.css']
})
export class UserCrudComponent implements OnInit {
  selectedUser: User = null;
  updatedUser: User = null;

  constructor(
    // private http: HttpClient,
    // private router: Router,
    // private authService: AuthService,
    private userSvc: UserService
  ) {}

  ngOnInit() {}

  public setUpdateUser() {
    this.updatedUser = Object.assign({}, this.selectedUser);
  }

  public updateUser(user: User) {
    this.userSvc.update(user).subscribe(
      uData => {
        this.updatedUser = null;
        this.selectedUser = null;
      },
      uErr => {
        console.log('UserCrud: Update Error');
      }
    );
  }
}
