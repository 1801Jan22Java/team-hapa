import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { enduser } from '../../types/enduser';
import { SessionService } from '../../services/session/session.service';
import { Subscription } from 'rxjs';
import { of } from 'rxjs/observable/of';

@Injectable()
export class FillAccountDetailsService {

  constructor(private http: HttpClient) { }

  // Keys for localStorage
  private user_id: string = "userID"
  private firstName: string = "firstName"
  private user_type: string = "user_type";

  private updated$: Observable<any>;

  autofill(): Observable<any> {
      this.updated$ = this.http.post<any>('http://localhost:8080/MACSAirport/util/fillprofile', { id :  localStorage.getItem(this.user_id) });

      return this.updated$;
  }

}
