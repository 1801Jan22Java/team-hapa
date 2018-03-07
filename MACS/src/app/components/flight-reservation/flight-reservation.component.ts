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
  selected: string;

  ngOnInit() {
    this.flight = this.details.flight;
    this.selected = "Economy";
  }

  confirm() {
    let fr = {
      flightID: 1,
      userID: this.session.getUserId(),
      type: this.selected
    }
    console.log(fr);
    this.http.post<{id: number}>("http://localhost:8080/MACSAirport/util/reserve",
      fr
    ).subscribe(data => {
      console.log(data);
    });
  }

  setSelected(val: string){
    this.selected = val;
    console.log(val)
  }
}
