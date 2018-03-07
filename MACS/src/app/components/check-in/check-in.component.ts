import { Component, OnInit } from '@angular/core';
import { FlightDetailService } from '../../services/flight-detail/flight-detail.service';
import { flight } from '../../types/flight';

@Component({
  selector: 'app-check-in',
  templateUrl: './check-in.component.html',
  styleUrls: ['./check-in.component.css']
})
export class CheckInComponent implements OnInit {

  constructor(private details: FlightDetailService) { }

  flight: flight;

  ngOnInit() {
    this.flight = this.details.getFlightDetails();
  }

}
