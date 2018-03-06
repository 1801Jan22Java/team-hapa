import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable'
import { auth } from '../../types/auth';
import { flight } from '../../types/flight';

@Injectable()
export class FlightSearchService {

  constructor(private http: HttpClient) { }

  flightResults: Observable<flight[]>;

  getFlightResults(earliestDate: string, destination: string) {
    this.flightResults = this.http.post<flight[]>('http://localhost:8080/MACSAirport/util/flight-search',
      { earliestDate: earliestDate, destination: destination });
  }

  getObservable(): Observable<flight[]>{
    return this.flightResults;
  }
  
}