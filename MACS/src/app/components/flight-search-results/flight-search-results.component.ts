import { Component, OnInit } from '@angular/core';
import { FlightSearchService } from '../../services/flight-search/flight-search.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-flight-search-results',
  templateUrl: './flight-search-results.component.html',
  styleUrls: ['./flight-search-results.component.css']
})
export class FlightSearchResultsComponent implements OnInit {

  constructor(private service: FlightSearchService) { }

  public flights: Subscription;

  ngOnInit() {
    this.flights = this.service.displayOpenFlights()
      .subscribe(data => console.log(data), error => console.log(error));
  }


}
