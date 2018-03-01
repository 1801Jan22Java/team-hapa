import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FlightSearchService } from '../../services/flight-search/flight-search.service';

@Component({
  selector: 'app-track-flight-status',
  templateUrl: './track-flight-status.component.html',
  styleUrls: ['./track-flight-status.component.css']
})
export class TrackFlightStatusComponent implements OnInit {

  constructor(private router: Router, private service: FlightSearchService) {
 
  }
  form = new FormGroup({
    date: new FormControl("", Validators.required),
    destination: new FormControl("", Validators.required)
  })

  ngOnInit() {
  }

  onSubmit(event){
    this.router.navigateByUrl('flight/details');
  }

}
