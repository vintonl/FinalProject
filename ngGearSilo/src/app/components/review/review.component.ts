import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { ReviewOfShopperService } from './../../services/review-of-shopper.service';
import { Reservation } from './../../models/reservation';
import { ReviewOfGear } from './../../models/review-of-gear';
import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';


@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {

  constructor(private reviewOfShopperSvc: ReviewOfShopperService, private router: Router, private authService: AuthService) { }

  ngOnInit() {
    const cred = this.authService.getCredentials();

    if (cred === null) {
      this.router.navigateByUrl('/login');

    }
  }


  createGearReview(gearReview: NgForm) {
    const newGearReview = {
      rating: gearReview.value.rating,
      review: gearReview.value.review,
      reservation: {
        id: '1'
      }
    };

    let user = new User();

    this.authService.getUserByUsername(this.authService.getLoggedInUsername()).subscribe(
      good => {
        user = good;
        console.log(user);
        this.reviewOfShopperSvc.createGearReview(newGearReview, user).subscribe(
          next => {
            console.log('ReviewComponent.createGearReview(): review of gear created.');
            this.router.navigateByUrl('/profile');
            console.log(next);
          },
          error => {
            console.error('ReviewComponent.createGearReview(): error createGearReview.');
            console.log(error);
          }
        );
      },
      error => {
        console.log('ReviewOfShopperService.create() Error getting logged in user while creating gear review');
      }
    );
  }


}
