import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable'
import { reservation } from '../../types/reservation';
import { SessionService } from '../../services/session/session.service';

@Injectable()
export class FlightHistoryService {

  constructor(private http: HttpClient, private session: SessionService ) { }

  flightResults$: Observable<reservation[]>;

  // Keys for localStorage
  private user_id: string = "userID"
  private firstName: string = "firstName"
  private user_type: string = "user_type";

  getFlightHistory(): Observable<reservation[]>{
    this.flightResults$ = this.http.post<reservation[]>('http://ec2-107-21-70-248.compute-1.amazonaws.com:8080/MACSAirport/util/flight-history', { id : localStorage.getItem(this.user_id) });
    return this.flightResults$;
  }

  getFlightHistoryObservable(): Observable<reservation[]>{
    return this.flightResults$;
  }
}
