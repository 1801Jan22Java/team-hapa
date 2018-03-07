import { Component, OnInit } from '@angular/core';
// import { FlightSearchComponent } from "../flight-search/flight-search.component";
import { HttpClient } from '@angular/common/http';
import { flight } from '../../types/flight';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private http: HttpClient) { }

  ngOnInit() {
  }

  addFlights() {
    this.http.post<flight[]>('http://localhost:8080/MACSAirport/util/admin/add-flights', {  }).subscribe();
  }
  
}
