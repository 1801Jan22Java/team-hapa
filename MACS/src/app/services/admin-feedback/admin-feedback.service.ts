import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { of } from 'rxjs/observable/of';

@Injectable()
export class AdminFeedbackService {

  constructor(private http: HttpClient) { }

  public feedback$: Observable<any>;

  getFeedback(): Observable<any> {
    this.feedback$ = this.http.post('/MACSAirport/util/admin/feedback', {});

    return this.feedback$;
  }
}
