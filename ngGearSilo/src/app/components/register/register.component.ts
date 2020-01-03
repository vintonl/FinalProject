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
    const user = {
      firstName: userForm.value.firstName,
      lastName: userForm.value.lastName,
      email: userForm.value.email,
      username: userForm.value.email,
      password: userForm.value.password,
      imageUrl: userForm.value.imageUrl,
      about: userForm.value.about,
      phone: userForm.value.phone,
      address: {
        address: userForm.value.address,
        address2: userForm.value.address2,
        city: userForm.value.city,
        state: userForm.value.state,
        postalCode: userForm.value.postalCode,
        country: 'USA'
      }

    };

    if (user.imageUrl.length < 10 || user.imageUrl === null || user.imageUrl === undefined) {
      user.imageUrl = "https://i.imgur.com/zVdNnTx.png";
    }

    // console.log('User in the registration comp');
    // console.log(userForm);

    this.auth.register(user).subscribe(
      data => {
        // console.log('RegisterComponent.register(): user registered.');
        // console.log(data);
        this.auth.login(user.username, user.password).subscribe(
          next => {
            // console.log('RegisterComponent.register(): user logged in, routing to /gears.');
            this.router.navigateByUrl('/gears');
            // console.log(next);
          },
          error => {
            // console.error('RegisterComponent.register(): error logging in.');
            // console.log(error);
          }
        );
      },
      err => {
        // console.error('RegisterComponent.register(): error registering.');
        console.error(err);
        // this.router.navigateByUrl('/register');
      }
    );
  }

}
