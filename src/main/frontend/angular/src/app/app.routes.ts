import { Routes } from '@angular/router';
import { HomeComponent } from '../pages/home.component';
import { SearchComponent } from '../pages/search.component';
import { AirportsComponent } from '../pages/airports.component';
import { LoginComponent } from '../pages/login.component';
import { RegistrationComponent } from '../pages/registration.component';
import { ProfileComponent } from '../pages/profile.component';
import { FlightDetailsComponent } from '../pages/flight-details.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'search', component: SearchComponent },
  { path: 'airports', component: AirportsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'flight/:flightId', component: FlightDetailsComponent },
  // перенаправление для несуществующих путей
  { path: '**', redirectTo: '' }
];
