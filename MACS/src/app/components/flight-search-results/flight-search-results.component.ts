import { Component, OnInit, OnDestroy } from '@angular/core';
import { FlightSearchService } from '../../services/flight-search/flight-search.service';
import { Subscription } from 'rxjs';
import { flight } from '../../types/flight';
import { FlightDetailService } from '../../services/flight-detail/flight-detail.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-flight-search-results',
  templateUrl: './flight-search-results.component.html',
  styleUrls: ['./flight-search-results.component.css']
})
export class FlightSearchResultsComponent implements OnInit {

  constructor(
    private service: FlightSearchService,
    private details: FlightDetailService,
    private router: Router
  ) { }

  flights: flight[];

  ngOnInit() {
    let hiddenDiv = document.getElementsByClassName("hidden") as HTMLCollectionOf<HTMLElement>;
    hiddenDiv[0].style.visibility = "hidden";
    this.service.flightResults.subscribe(
      data => {
        this.flights = data;
        if (this.flights.length == 0) {
          hiddenDiv[0].style.visibility = "visible";
        }
      }
    );
  }

  flightDetails(flight: flight) {
    this.details.setFlightDetails(flight);
    this.router.navigateByUrl("flight/details")
  }

}