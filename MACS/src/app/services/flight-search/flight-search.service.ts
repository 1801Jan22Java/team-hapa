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
    this.flightResults = this.http.post<flight[]>('http://ec2-107-21-70-248.compute-1.amazonaws.com:8080/MACSAirport/util/flight-search',
      { earliestDate: earliestDate, destination: destination });
  }

  getObservable(): Observable<flight[]>{
    return this.flightResults;
  }

  displayArrivalsDepartures():Observable<any>{
    return this.http.post('http://ec2-107-21-70-248.compute-1.amazonaws.com:8080/MACSAirport/util/all-flights', '');
  }
  
}