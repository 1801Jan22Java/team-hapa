import { Component, OnInit } from '@angular/core';
import { FlightDetailService } from '../../services/flight-detail/flight-detail.service';
import { flight } from '../../types/flight';
import { SessionService } from '../../services/session/session.service';
import { HttpClient } from '@angular/common/http';
import { flightDetails } from '../../types/flightDetails';
import { Router } from '@angular/router';

@Component({
  selector: 'app-flight-details',
  templateUrl: './flight-details.component.html',
  styleUrls: ['./flight-details.component.css']
})
export class FlightDetailsComponent implements OnInit {

  flight: flight;
  status: string;
  available: boolean = false;
  reservation: number;

  constructor(
    private details: FlightDetailService,
    private session: SessionService,
    private http: HttpClient,
    private router: Router
  ) { }

  ngOnInit() {
    this.flight = this.details.getFlightDetails();
    let time = this.flight.time;
    let date = new Date(time);
    let now = new Date();
    this.available = date > now;
    this.http.post<{ id: number, status: { refValue: string } }>('http://localhost:8080/MACSAirport/util/check-reserved',
      {
        userID: this.session.getUserId(),
        flightID: this.flight.id
      })
      .subscribe(
        data => {
          this.reservation = data.id;
          this.status = data.status.refValue;
          console.log(status)
        },
        error => {
          this.status = error.status.refValue;
        }
      )
  }

  cancel() {
    this.http.post('http://localhost:8080/MACSAirport/util/cancel', this.reservation)
      .subscribe();
  }

}
