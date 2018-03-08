import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { RegistrationInfo } from '../../types/registration';
import { Observable } from 'rxjs/Observable';

import { SessionService } from '../../services/session/session.service';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { catchError } from 'rxjs/operators';

@Component({
  selector: 'app-account-registration',
  templateUrl: './account-registration.component.html',
  styleUrls: ['./account-registration.component.css']
})
export class AccountRegistrationComponent implements OnInit {


  emailErrorMessage: string;
  passwordErrorMessage: string;
  registerResponse: Observable<any>;

  constructor(
    private router: Router,
    public session: SessionService,
    private http: HttpClient
  ) { }

  form = new FormGroup({
    firstname: new FormControl("", Validators.required),
    lastname: new FormControl("", Validators.required),
    email: new FormControl("", Validators.required),
    password: new FormControl("", Validators.required),
    confirmPassword: new FormControl("", Validators.required),
    answer1: new FormControl("", Validators.required),
    answer2: new FormControl("", Validators.required),
    answer3: new FormControl("", Validators.required)
  })


  ngOnInit() {
  }

  onSubmit(): void {
    // console.log(this.form.get('date').value);
    let registrationInfo: RegistrationInfo = {
      firstname: this.form.get("firstname").value,
      lastname: this.form.get("lastname").value,
      email: this.form.get("email").value,
      password: this.form.get("password").value,
      type: "Passenger",
      answer1: this.form.get("answer1").value,
      answer2: this.form.get("answer2").value,
      answer3: this.form.get("answer3").value
    }

    if(registrationInfo.password == this.form.get("confirmPassword").value){
      this.http.post<any>(
        'http://ec2-107-21-70-248.compute-1.amazonaws.com:8080/MACSAirport/util/register',
        registrationInfo).subscribe(
          data=>{
            this.session.setSession(data);
            this.router.navigateByUrl("home");
          },
          error=>this.emailErrorMessage = "Email already taken."
      )
    } else {
      this.passwordErrorMessage = "Passwords do not match."
    }
  } 


  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an ErrorObservable with a user-facing error message
    return new ErrorObservable(
      'Something bad happened; please try again later.');
  };

}
