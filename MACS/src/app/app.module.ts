import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
// Commented out, creating a new routermodule modle
// import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './home/home.component';

import { AppRouterModule } from './app-router/app-router.module';
import { NavbarComponent } from './navbar/navbar.component';
import { ArrivalsDeparturesComponent } from './arrivals-departures/arrivals-departures.component';

import { Test1Component } from './test1/test1.component';
import { Test2Component } from './test2/test2.component';



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
    Test1Component,
    Test2Component
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
