import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { HomeComponent } from './components/home/home.component';

import { AppRouterModule } from './app-router/app-router.module';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ArrivalsDeparturesComponent } from './components/arrivals-departures/arrivals-departures.component';
import { FlightSearchComponent } from './components/flight-search/flight-search.component';
import { TrackFlightStatusComponent } from './components/track-flight-status/track-flight-status.component';
import { FlightReservationComponent } from './components/flight-reservation/flight-reservation.component';
import { AccountRegistrationComponent } from './components/account-registration/account-registration.component';
import { ChangeAccountDetailsComponent } from './components/change-account-details/change-account-details.component';
import { ViewReservationsComponent } from './components/view-reservations/view-reservations.component';
import { ResetPasswordComponent } from './components/reset-password/reset-password.component';
import { ViewItineraryComponent } from './components/view-itinerary/view-itinerary.component';
import { SubmitFeedbackComponent } from './components/submit-feedback/submit-feedback.component';
import { ViewFlightHistoryComponent } from './components/view-flight-history/view-flight-history.component';
import { CheckInComponent } from './components/check-in/check-in.component';
import { FlightSearchResultsComponent } from './components/flight-search-results/flight-search-results.component';
import { TrackFlightStatusResultsComponent } from './components/track-flight-status-results/track-flight-status-results.component';
import { FooterComponent } from './components/footer/footer.component';
import { LoginComponent } from './components/login/login.component';
import { FlightDetailsComponent } from './components/flight-details/flight-details.component';
import { AdminFeedbackComponent } from './components/admin-feedback/admin-feedback.component';
import { AdminUsersComponent } from './components/admin-users/admin-users.component';

//Services
import { FlightSearchService } from './services/flight-search/flight-search.service';
import { SessionService } from './services/session/session.service';
import { AuthGuardService } from './services/auth-guard/auth-guard.service';
import { UserAuthGuardService } from './services/user-auth-guard/user-auth-guard.service';
import { VisitorAuthGuardService } from './services/visitor-auth-guard/visitor-auth-guard.service';
import { ChangeAccountDetailsService } from './services/change-account-details/change-account-details.service';
import { FlightHistoryService } from './services/flight-history/flight-history.service';
import { FillAccountDetailsService } from './services/fill-account-details/fill-account-details.service';
import { FlightDetailService } from './services/flight-detail/flight-detail.service';

// Create an array of routes.
// Commented out to impelement app-router externally
// const appRoutes: Routes = [
//   { path: 'dashboard', component: DashboardComponent },
//   { path: '', component: HomeComponent },
// ];
@NgModule({
  declarations: [
    AppComponent,
    // Dashboard component created and added for demo
    DashboardComponent,
    HomeComponent,
    NavbarComponent,
    ArrivalsDeparturesComponent,
    FlightSearchComponent,
    TrackFlightStatusComponent,
    FlightReservationComponent,
    AccountRegistrationComponent,
    ChangeAccountDetailsComponent,
    ViewReservationsComponent,
    ResetPasswordComponent,
    ViewItineraryComponent,
    SubmitFeedbackComponent,
    ViewFlightHistoryComponent,
    CheckInComponent,
    FlightSearchResultsComponent,
    TrackFlightStatusResultsComponent,
    FooterComponent,
    LoginComponent,
    FlightDetailsComponent,
    AdminFeedbackComponent,
    AdminUsersComponent,
  ],
  imports: [
    BrowserModule,
    // Add Router Module. First example is with appRoutes hard coded in 
    // AppModule. Commented out to implement app-router externally
    // RouterModule.forRoot(appRoutes)
    AppRouterModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    FlightSearchService,
    SessionService,
    AuthGuardService,
    UserAuthGuardService,
    VisitorAuthGuardService,
    ChangeAccountDetailsService,
    FlightHistoryService,
    FillAccountDetailsService,
    FlightDetailService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
