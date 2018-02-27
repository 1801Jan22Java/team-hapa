import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent } from '../components/dashboard/dashboard.component';
import { HomeComponent } from '../home/home.component';

import { NavbarComponent } from '../components/navbar/navbar.component';
import { ArrivalsDeparturesComponent } from '../components/arrivals-departures/arrivals-departures.component';
import { FlightSearchComponent } from '../components/flight-search/flight-search.component';
import { TrackFlightStatusComponent } from '../components/track-flight-status/track-flight-status.component';
import { FlightReservationComponent } from '../components/flight-reservation/flight-reservation.component';
import { AccountRegistrationComponent } from '../components/account-registration/account-registration.component';
import { ChangeAccountDetailsComponent } from '../components/change-account-details/change-account-details.component';
import { ViewReservationsComponent } from '../components/view-reservations/view-reservations.component';
import { ResetPasswordComponent } from '../components/reset-password/reset-password.component';
import { ViewItineraryComponent } from '../components/view-itinerary/view-itinerary.component';
import { SubmitFeedbackComponent } from '../components/submit-feedback/submit-feedback.component';
import { ViewFlightHistoryComponent } from '../components/view-flight-history/view-flight-history.component';
import { CheckInComponent } from '../components/check-in/check-in.component';


const appRoutes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'app-dashboard', component: DashboardComponent },
  { path: 'app-arrivals-departures', component: ArrivalsDeparturesComponent },
  { path: 'app-flight-search', component: FlightSearchComponent },
  { path: 'app-track-flight-status', component: TrackFlightStatusComponent },
  { path: 'app-flight-reservation', component: FlightReservationComponent },
  { path: 'app-account-registration', component: AccountRegistrationComponent },
  { path: 'app-change-account-details', component: ChangeAccountDetailsComponent },
  { path: 'app-view-reservations', component: ViewReservationsComponent },
  { path: 'app-reset-password', component: ResetPasswordComponent },
  { path: 'app-view-itinerary', component: ViewItineraryComponent },
  { path: 'app-submit-feedback', component: SubmitFeedbackComponent },
  { path: 'app-view-flight-history', component: ViewFlightHistoryComponent },
  { path: 'app-check-in-component', component: CheckInComponent },
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(appRoutes)
  ],
  declarations: [],
  exports:[ RouterModule ]
})
export class AppRouterModule { }
