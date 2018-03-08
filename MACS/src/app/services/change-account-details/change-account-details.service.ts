import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { auth } from '../../types/auth';
import { user } from '../../types/user';
import { updateinfo } from '../../types/updateinfo';
import { Subscription } from 'rxjs';
import { of } from 'rxjs/observable/of';

@Injectable()
export class ChangeAccountDetailsService {


  constructor(private http: HttpClient) { }

  private updated$: Observable<any>;

  update(firstname: string, lastname: string, email: string, newpassword: string, answer1: string,
    answer2: string, answer3: string): Observable<any> {
      let user: updateinfo = {firstname, lastname, email, newpassword, answer1, answer2, answer3};
      this.updated$ = this.http.post<any>('http://ec2-107-21-70-248.compute-1.amazonaws.com:8080/MACSAirport/util/profile', user);

      return this.updated$;
  }

}
