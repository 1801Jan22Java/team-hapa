import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { RegistrationInfo } from '../../types/registration';
import { Observable } from 'rxjs/Observable';

import { SessionService } from '../../services/session/session.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-account-registration',
  templateUrl: './account-registration.component.html',
  styleUrls: ['./account-registration.component.css']
})
export class AccountRegistrationComponent implements OnInit {


  emailErrorMessage: string;
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

    this.http.post<any>(
      'http://localhost:8080/MACSAirport/util/register',
      registrationInfo)
      .subscribe(
        data => {
          console.log(data)
          // this.router.navigateByUrl('/home');
          this.emailErrorMessage = '';
          // Login after registering
          // this.session.login(
          //   this.form.get("email").value,
          //   this.form.get("password").value)
          //   .subscribe(
          //     data1 => {
          //       this.session.setSession(data);
          //       this.router.navigateByUrl('reservation/history');
          //       this.emailErrorMessage = "";
              // }
            // )
        },
        error => {
          this.emailErrorMessage = "Email Taken";
        }
      )

    this.router.navigateByUrl('');
  }

}
