import { User } from './../../models/user';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  isAdmin = false;
  loggedInUser: User;

  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit() {
    this.loadAdmin();
  }

  userLogInCheck() {
    return this.authService.getCredentials();
  }

  logout() {
    this.authService.logout();
    this.ngOnInit();
    this.router.navigateByUrl('/login');
  }

  loadAdmin() {
    let userLoggedIn: User = null;

    if (this.authService.getCredentials() !== null) {
      this.authService
        .getUserByUsername(this.authService.getLoggedInUsername())
        .subscribe(
          yes => {
            userLoggedIn = yes;
            this.loggedInUser = userLoggedIn;
            // console.log(userLoggedIn);
            if (userLoggedIn.role === 'admin') {
              // console.log('admin is logged in');
              return this.isAdmin = true;
            }
          },
          no => {
            userLoggedIn = null;
            // console.error('Error getting logged admin');
            // console.error(no);
            this.isAdmin = false;
          }
        );
    }
    return this.isAdmin = false;
  }
}
