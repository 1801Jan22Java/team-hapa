import { Component, OnInit } from '@angular/core';
import { FlightDetailService } from '../../services/flight-detail/flight-detail.service';
import { HttpClient } from '@angular/common/http';
import { SessionService } from '../../services/session/session.service';
import { flight } from '../../types/flight';
import { flightDetails } from '../../types/flightDetails';

@Component({
  selector: 'app-view-reservations',
  templateUrl: './view-reservations.component.html',
  styleUrls: ['./view-reservations.component.css']
})
export class ViewReservationsComponent implements OnInit {

  constructor(
    private details: FlightDetailService,
    private http: HttpClient,
    public session: SessionService
  ) { }

  flight: flight;
  available: boolean;

  ngOnInit() {
    this.flight = this.details.flight;
    this.http.post<flightDetails>('http://ec2-107-21-70-248.compute-1.amazonaws.com:8080/MACSAirport/util/flight-details', {id: this.flight.id})
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
