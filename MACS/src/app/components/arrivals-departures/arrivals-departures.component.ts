import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FlightSearchService } from '../../services/flight-search/flight-search.service';
import { FlightDetailService } from '../../services/flight-detail/flight-detail.service';
import { flight } from '../../types/flight';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-arrivals-departures',
  templateUrl: './arrivals-departures.component.html',
  styleUrls: ['./arrivals-departures.component.css']
})
export class ArrivalsDeparturesComponent implements OnInit {

  constructor(
    private router: Router, 
    private service: FlightSearchService, 
    private details: FlightDetailService
  ) { }

  public flights: any[];
  public arrivals: any[];
  public departures: any[];
  public sub : Subscription;

  ngOnInit() {
    this.sub = this.service.displayArrivalsDepartures()
      .subscribe(data => {
        this.flights=data;
        console.log(this.flights);

        this.arrivals = [];
        this.departures = [];
        console.log(this.arrivals);
        console.log(this.departures);
        this.flights.forEach(element => {
          if (element.type.refValue == "Arrival") {
            this.arrivals.push(element);
          } else {
            this.departures.push(element);
          }

        });
      });
  }

  flightDetails(flight: flight) {
    this.details.setFlightDetails(flight);
    this.router.navigateByUrl("flight/details")
  }

}
