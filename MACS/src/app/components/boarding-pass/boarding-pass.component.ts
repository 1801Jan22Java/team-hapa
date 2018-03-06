import { Component, OnInit } from '@angular/core';
import { FlightDetailService } from '../../services/flight-detail/flight-detail.service';
import { HttpClient } from '@angular/common/http';
import { SessionService } from '../../services/session/session.service';
import { flight } from '../../types/flight';
import { flightDetails } from '../../types/flightDetails';

@Component({
  selector: 'app-view-reservations',
  templateUrl: './boarding-pass.component.html',
  styleUrls: ['./boarding-pass.component.css']
})
export class BoardingPassComponent implements OnInit {

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