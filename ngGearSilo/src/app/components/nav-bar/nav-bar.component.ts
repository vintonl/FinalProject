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
  userLoggedIn: User = null;

  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit() {
    if (this.authService.checkLogin()) {
      this.loadUser();
    }
  }

  logout() {
    // this.userLoggedIn = null;
    this.authService.logout();
    this.loadUser();
    this.router.navigateByUrl('/login');
  }

  loadUser() {
    this.userLoggedIn = new User();

    this.authService.getUserByUsername(this.authService.getLoggedInUsername()).subscribe(
      yes => {
        this.userLoggedIn = yes;
        console.log('Got logged in user:');
        console.log(this.userLoggedIn);
      },
      no => {
        this.userLoggedIn = null;
        console.error('Error getting logged in user');
        console.error(no);
      }
    );
    return this.userLoggedIn;
  }
}
