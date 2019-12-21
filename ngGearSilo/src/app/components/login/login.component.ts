import { NavBarComponent } from './../nav-bar/nav-bar.component';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, private auth: AuthService, private navbar: NavBarComponent) { }

  ngOnInit() {
  }

  login(loginForm: NgForm) {
    const user: User = new User();
    user.username = loginForm.value.username;
    user.password = loginForm.value.password;

    this.auth.login(user.username, user.password).subscribe(
      next => {
        console.log('LoginComponent.login(): user logged in, routing to /user.');
        console.log(next);
        this.navbar.loadUser();

        this.router.navigateByUrl('/navbar', { skipLocationChange: true }).then(() => {
          this.router.navigate(['/users']);
        });
      },
      error => {
        console.error('LoginComponent.login(): error logging in.');
        console.error(error);
        this.router.navigateByUrl('/login');
      }
    );
  }

}
