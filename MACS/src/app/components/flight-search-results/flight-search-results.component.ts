import { Component, OnInit, OnDestroy } from '@angular/core';
import { FlightSearchService } from '../../services/flight-search/flight-search.service';
import { Subscription } from 'rxjs';
import { flight } from '../../types/flight';


@Component({
  selector: 'app-flight-search-results',
  templateUrl: './flight-search-results.component.html',
  styleUrls: ['./flight-search-results.component.css']
})
export class FlightSearchResultsComponent implements OnInit {

  constructor(private service: FlightSearchService) { }

  flights: flight[];

  ngOnInit() {
    this.service.flightResults.subscribe(
      data => {
        this.flights = data;
      }
    );
  }

  flightDetails(flightId: number){
    console.log(flightId);
  }
}
