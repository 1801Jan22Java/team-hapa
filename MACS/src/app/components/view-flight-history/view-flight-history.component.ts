import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FlightHistoryService } from '../../services/flight-history/flight-history.service';
import { FlightDetailService } from '../../services/flight-detail/flight-detail.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { reservation } from '../../types/reservation';
import { flight } from '../../types/flight';
import { Observable } from 'rxjs/Observable';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-view-flight-history',
  templateUrl: './view-flight-history.component.html',
  styleUrls: ['./view-flight-history.component.css']
})
export class ViewFlightHistoryComponent implements OnInit {

  // Keys for localStorage
  private user_id: string = "userID"
  private firstName: string = "firstName"
  private user_type: string = "user_type"; 

  flightHistory$: Observable<any>;

  flights: flight[];

  constructor(private router: Router, private service: FlightHistoryService, private details: FlightDetailService, private http: HttpClient) { }

  ngOnInit() {
    this.service.getFlightHistory();
    this.http.post<flight[]>('/MACSAirport/util/flight-history', { id : localStorage.getItem(this.user_id) }).subscribe(
      data => {
        
        this.flights = [];
        
        data.sort(function(a, b) {
          return new Date(a.time)>new Date(b.time) ? -1 : new Date(a.time)<new Date(b.time) ? 1 : 0;
        });
        this.flights = data;
      }
    )
    /*this.service.flightResults$.subscribe(
      data => {
        this.flights = data;
      }
    );*/
  }

  flightDetails(flight: flight){
    this.details.setFlightDetails(flight);
    this.router.navigateByUrl("flight/details");
  }

  
}
