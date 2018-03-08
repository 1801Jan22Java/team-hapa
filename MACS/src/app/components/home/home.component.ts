import { Component, OnInit } from '@angular/core';
// import { FlightSearchComponent } from "../flight-search/flight-search.component";
import { HttpClient } from '@angular/common/http';
import { flight } from '../../types/flight';
import { SessionService } from '../../services/session/session.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private http: HttpClient, public session: SessionService) { }

  ngOnInit() {
  }

  addFlights() {
    this.http.post<flight[]>('http://ec2-107-21-70-248.compute-1.amazonaws.com:8080/MACSAirport/util/admin/add-flights', {  }).subscribe();
  }
  
}
