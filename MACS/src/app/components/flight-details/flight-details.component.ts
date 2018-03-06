import { Component, OnInit } from '@angular/core';
import { FlightDetailService } from '../../services/flight-detail/flight-detail.service';
import { flight } from '../../types/flight';
import { SessionService } from '../../services/session/session.service';
import { HttpClient } from '@angular/common/http';
import { flightDetails } from '../../types/flightDetails';

@Component({
  selector: 'app-flight-details',
  templateUrl: './flight-details.component.html',
  styleUrls: ['./flight-details.component.css']
})
export class FlightDetailsComponent implements OnInit {

  flight: flight;
  status: string;
  available: boolean = false;

  constructor(
    private details: FlightDetailService,
    private session: SessionService,
    private http: HttpClient
  ) { }

  ngOnInit() {
    this.flight = this.details.getFlightDetails();
    this.http.post<flightDetails>('http://localhost:8080/MACSAirport/util/flight-details', {id: this.flight.id})
      .subscribe(
        data=>{
          this.status = data.status.refValue;
          let time = data.flight.time;
          let date = new Date(time);
          let now= new Date();
          this.available = date > now;
        }
      )
  }

}
