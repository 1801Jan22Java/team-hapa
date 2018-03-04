import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-flight-search',
  templateUrl: './flight-search.component.html',
  styleUrls: ['./flight-search.component.css']
})
export class FlightSearchComponent implements OnInit {

  constructor(private router : Router) { }

  ngOnInit() {
  }

  submit(): void {
    this.router.navigateByUrl('flightsearch');
  }

}
