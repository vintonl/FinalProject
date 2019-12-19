import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private router: Router, private auth: AuthService) { }

  ngOnInit() {
  }

  register(userForm: NgForm) {
    const user: User = userForm.value;

    this.auth.register(user).subscribe(
      data => {
        console.log('RegisterComponent.register(): user registered.');
        console.log(data);
        this.auth.login(user.username, user.password).subscribe(
          next => {
            console.log('RegisterComponent.register(): user logged in, routing to /todo.');
            this.router.navigateByUrl('/gears');
            console.log(next);
          },
          error => {
            console.error('RegisterComponent.register(): error logging in.');
            console.log(error);
            this.router.navigateByUrl('/login');
          }
        );
      },
      err => {
        console.error('RegisterComponent.register(): error registering.');
        console.error(err);
        this.router.navigateByUrl('/register');
      }
    );
  }

}
