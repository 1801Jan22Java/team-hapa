import { Component, OnInit } from '@angular/core';
import { FlightDetailService } from '../../services/flight-detail/flight-detail.service';
import { HttpClient } from '@angular/common/http';
import { flightDetails } from '../../types/flightDetails';
import { flight } from '../../types/flight';
import { SessionService } from '../../services/session/session.service';

@Component({
  selector: 'app-flight-reservation',
  templateUrl: './flight-reservation.component.html',
  styleUrls: ['./flight-reservation.component.css']
})
export class FlightReservationComponent implements OnInit {

  constructor(
    private details: FlightDetailService,
    private http: HttpClient,
    private session: SessionService
  ) { }

  flight: flight;
  available: boolean;

  ngOnInit() {
    this.flight = this.details.flight;
    this.http.post<flightDetails>('http://localhost:8080/MACSAirport/util/flight-details', {id: this.flight.id})
      .subscribe(
        data=>{
          let time = data.flight.time;
          let date = new Date(time);
          let now= new Date();
          this.available = date > now;
        }
      );
  }
}
