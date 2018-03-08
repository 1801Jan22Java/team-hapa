import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { auth } from '../../types/auth';
import { Subscription } from 'rxjs';
import { of } from 'rxjs/observable/of';
import { passwordreset } from '../../types/passwordreset';

@Injectable()
export class ResetPasswordService {

  constructor(private http: HttpClient) { }

  private updated$: Observable<any>;

  updatePassword(resetinfo : passwordreset): Observable<any> {
    this.updated$ = this.http.post<any>('http://ec2-107-21-70-248.compute-1.amazonaws.com:8080/MACSAirport/util/reset', resetinfo);

    return this.updated$;
  }

}
