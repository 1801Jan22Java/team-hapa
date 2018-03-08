import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FlightSearchService } from '../../services/flight-search/flight-search.service';
import { HttpClient } from '@angular/common/http';
import { flight } from '../../types/flight';
import { FlightDetailService } from '../../services/flight-detail/flight-detail.service';
import { flightDetails } from '../../types/flightDetails';

@Component({
  selector: 'app-track-flight-status',
  templateUrl: './track-flight-status.component.html',
  styleUrls: ['./track-flight-status.component.css']
})
export class TrackFlightStatusComponent implements OnInit {

  flight: flight;

  constructor(private router: Router,
    private service: FlightSearchService,
    private http: HttpClient,
    private details: FlightDetailService) {

  }
  form = new FormGroup({
    flightID: new FormControl("", Validators.required)
  })

  ngOnInit() {
  }

  onSubmit() {
    let id = parseInt(this.form.get("flightID").value);
    console.log(id)
    this.http.post<flightDetails>('http://ec2-107-21-70-248.compute-1.amazonaws.com:8080/MACSAirport/util/flight-details',
      { id: id }
    ).subscribe(
      data => {
        this.details.setFlightDetails(data.flight);
        this.router.navigateByUrl('flight/details');
      }
    ) 
  }

}
