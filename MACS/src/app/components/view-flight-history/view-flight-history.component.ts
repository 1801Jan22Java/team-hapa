import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FlightHistoryService } from '../../services/flight-history/flight-history.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { flight } from '../../types/flight';
import { Observable } from 'rxjs/Observable';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-view-flight-history',
  templateUrl: './view-flight-history.component.html',
  styleUrls: ['./view-flight-history.component.css']
})
export class ViewFlightHistoryComponent implements OnInit {

  flightHistory$: Observable<any>;

  flights: flight[];

  constructor(private router: Router, private service: FlightHistoryService) { }

  ngOnInit() {
    this.service.getFlightHistory();
    this.service.flightResults$.subscribe(
      data => {
        this.flights = data;
      }
    );
  }

  
}
