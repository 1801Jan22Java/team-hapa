import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable'
import { flight } from '../../types/flight';
import { SessionService } from '../../services/session/session.service';

@Injectable()
export class FlightHistoryService {

  constructor(private http: HttpClient, private session: SessionService ) { }

  flightResults$: Observable<flight[]>;

  // Keys for localStorage
  private user_id: string = "userID"
  private firstName: string = "firstName"
  private user_type: string = "user_type";

  getFlightHistory(): Observable<flight[]>{
    this.flightResults$ = this.http.post<flight[]>('http://localhost:8080/MACSAirport/util/flight-history', { id : localStorage.getItem(this.user_id) });
    return this.flightResults$;
  }

  getFlightHistoryObservable(): Observable<flight[]>{
    return this.flightResults$;
  }
}
