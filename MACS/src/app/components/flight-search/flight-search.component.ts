import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FlightSearchService } from '../../services/flight-search/flight-search.service';

@Component({
  selector: 'app-flight-search',
  templateUrl: './flight-search.component.html',
  styleUrls: ['./flight-search.component.css']
})
export class FlightSearchComponent implements OnInit {

  constructor(private router: Router, private service: FlightSearchService) {
 
  }

  valuedate = new Date();

  form = new FormGroup({
    date: new FormControl("", Validators.required),
    destination: new FormControl("", Validators.required)
  })


  ngOnInit() {
  }

  onSubmit(): void {
    let date = this.form.get("date").value;
    let destination = this.form.get("destination").value;

    this.service.getFlightResults(date, destination);
    
    this.router.navigateByUrl('flightsearch')
  }

}
