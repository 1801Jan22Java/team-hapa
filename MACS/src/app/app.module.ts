import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
// Commented out, creating a new routermodule modle
// import { RouterModule, Routes } from '@angular/router';

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
  ],
  imports: [
    BrowserModule,
    // Add Router Module. First example is with appRoutes hard coded in 
    // AppModule. Commented out to implement app-router externally
    // RouterModule.forRoot(appRoutes)
    AppRouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
