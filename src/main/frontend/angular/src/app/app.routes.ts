import { Routes } from '@angular/router';
import { HomeComponent } from '../pages/home/home.component';
import { SearchComponent } from '../pages/search/search.component';
import { AirportsComponent } from '../pages/airports/airports.component';
import { LoginComponent } from '../pages/login/login.component';
import { RegistrationComponent } from '../pages/registration/registration.component';
import { ProfileComponent } from '../pages/profile/profile.component';
import { FlightDetailsComponent } from '../pages/flight-details/flight-details.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'search', component: SearchComponent },
  { path: 'airports', component: AirportsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'flight/:flightId', component: FlightDetailsComponent },
  { path: '**', redirectTo: '' }
];
