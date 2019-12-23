import { CategoryService } from './services/category.service';
import { AddressService } from './services/address.service';
import { UserService } from './services/user.service';
import { ReviewOfShopperService } from './services/review-of-shopper.service';
import { ReviewOfLenderService } from './services/review-of-lender.service';
import { ReservationService } from './services/reservation.service';
import { AuthService } from 'src/app/services/auth.service';
import { GearService } from './services/gear.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { HomeComponent } from './components/home/home.component';
import { FooterComponent } from './components/footer/footer.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { GearListComponent } from './components/gear-list/gear-list.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { RegisterComponent } from './components/register/register.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ReservationMessageService } from './services/reservation-message.service';
import { DatePipe } from '@angular/common';
import { ProfileComponent } from './components/profile/profile.component';
import { ProfileService } from './services/profile.service';
import { AdminComponent } from './components/admin/admin.component';
import { TestingComponent } from './components/testing/testing.component';
import { MatSelectModule } from '@angular/material/select';

import {
  MatButtonModule, MatFormFieldModule, MatInputModule, MatSliderModule,
  MatIconModule, MatCardModule, MatDialogModule, MatProgressBar
} from '@angular/material';
import { ReservationComponent } from './components/reservation/reservation.component';
import { CategoryListComponent } from './category-list/category-list.component';
import { AboutComponent } from './components/about/about.component';


@NgModule({
  declarations: [
    AppComponent,
    NotFoundComponent,
    NavBarComponent,
    HomeComponent,
    FooterComponent,
    GearListComponent,
    LoginComponent,
    LogoutComponent,
    RegisterComponent,
    ProfileComponent,
    AdminComponent,
    TestingComponent,
    ReservationComponent,
    CategoryListComponent,
    AboutComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
    MatSelectModule

  ],
  providers: [
    GearService,
    AuthService,
    ReservationService,
    ReservationMessageService,
    DatePipe,
    ReviewOfLenderService,
    ReviewOfShopperService,
    UserService,
    ProfileService,
    NavBarComponent,
    GearListComponent,
    AddressService,
    CategoryService
  ],
  bootstrap: [AppComponent, ProfileComponent],

})
export class AppModule { }
