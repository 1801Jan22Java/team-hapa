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
    public session: SessionService,
    private http: HttpClient,
    private router: Router
  ) { }

  ngOnInit() {
    this.flight = this.details.getFlightDetails();
    let time = this.flight.time;
    let date = new Date(time);
    let now = new Date();
    this.available = date > now;
    this.http.post<{ id: number, status: { refValue: string } }>('/MACSAirport/util/check-reserved',
      {
        userID: this.session.getUserId(),
        flightID: this.flight.id,

      })
      .subscribe(
        data => {
          this.reservation = data.id;
          this.status = data.status.refValue;
          console.log(this.reservation + " " + this.status+" "+ this.available)
        },
        error => {
          this.status = "Cancelled";
          console.log(this.reservation + " " + this.status+" "+ this.available)
        }
      )
  }

  cancel() {
    this.http.post('/MACSAirport/util/cancel', this.reservation)
      .subscribe();
  }

}
