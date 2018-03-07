import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { SessionService } from '../../services/session/session.service';
import { submitFeedback } from '../../types/submitFeedback';
import { Subscription } from 'rxjs';
import { of } from 'rxjs/observable/of';

@Injectable()
export class SubmitFeedbackService {

  constructor(private session: SessionService, private http: HttpClient) { }

  public feedback$: Observable<any>

  // Keys for localStorage
  private user_id: string = "userID"
  private firstName: string = "firstName"
  private user_type: string = "user_type";
  public feedback: submitFeedback;

  submitFeedback(message: string): Observable<any>{
    this.feedback = { userId : localStorage.getItem(this.user_id), message : message }
    this.feedback$ = this.http.post('http://localhost:8080/MACSAirport/util/feedback', this.feedback );
    return this.feedback$;
  }
}
