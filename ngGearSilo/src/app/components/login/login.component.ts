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

  constructor(private router: Router, private auth: AuthService) { }

  ngOnInit() {
  }

  login(loginForm: NgForm) {
    const user: User = new User();
    user.username = loginForm.value.username;
    user.password = loginForm.value.password;
    // const user: User = loginForm.value;

    this.auth.login(user.username, user.password).subscribe(
      next => {
        console.log('LoginComponent.login(): user logged in, routing to /todo.');
        this.router.navigateByUrl('/todo');
      },
      error => {
        console.error('LoginComponent.login(): error logging in.');
        this.router.navigateByUrl('/login');
      }
    );
  }

}
