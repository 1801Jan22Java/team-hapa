import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent } from '../components/dashboard/dashboard.component';
import { HomeComponent } from '../components/home/home.component';

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
import { FlightSearchResultsComponent } from '../components/flight-search-results/flight-search-results.component';
import { TrackFlightStatusResultsComponent } from '../components/track-flight-status-results/track-flight-status-results.component';
import { LoginComponent } from '../components/login/login.component';
import { FlightDetailsComponent } from '../components/flight-details/flight-details.component'
import { AdminFeedbackComponent } from '../components/admin-feedback/admin-feedback.component';
import { AdminUsersComponent } from '../components/admin-users/admin-users.component';

// Route guards
import { AuthGuardService } from '../services/auth-guard/auth-guard.service';
import { UserAuthGuardService } from '../services/user-auth-guard/user-auth-guard.service';
import { VisitorAuthGuardService } from '../services/visitor-auth-guard/visitor-auth-guard.service';

const appRoutes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },

  { path: 'flightsearch', component: FlightSearchResultsComponent },
  { path: 'flightstatus', component: TrackFlightStatusResultsComponent },
  { path: 'flight/details', component: FlightDetailsComponent },

  {
    path: 'register',
    component: AccountRegistrationComponent,
    canActivate: [VisitorAuthGuardService]
  },
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [VisitorAuthGuardService]
  },

  {
    path: 'feedback',
    component: SubmitFeedbackComponent,
    canActivate: [UserAuthGuardService]
  },
  {
    path: 'profile',
    component: ChangeAccountDetailsComponent,
    canActivate: [UserAuthGuardService]
  },
  {
    path: 'reservation/history',
    component: ViewFlightHistoryComponent,
    canActivate: [UserAuthGuardService]
  },
  {
    path: 'reservation/confirm',
    component: ViewReservationsComponent,
    canActivate: [UserAuthGuardService]
  },
  {
    path: 'checkin',
    component: ViewItineraryComponent,
    canActivate: [UserAuthGuardService]
  },

  {
    path: 'admin/feedback',
    component: AdminFeedbackComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'admin/users',
    component: AdminUsersComponent,
    canActivate: [AuthGuardService]
  },

  { path: 'reservation/confirm', component: ViewReservationsComponent },

  { path: 'app-arrivals-departures', component: ArrivalsDeparturesComponent },
  { path: 'app-flight-search', component: FlightSearchComponent },
  { path: 'app-flight-reservation', component: FlightReservationComponent },
  { path: 'app-reset-password', component: ResetPasswordComponent },
  { path: 'app-check-in-component', component: CheckInComponent },
  { path: '**', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(appRoutes)
  ],
  declarations: [],
  exports: [RouterModule]
})
export class AppRouterModule { }
