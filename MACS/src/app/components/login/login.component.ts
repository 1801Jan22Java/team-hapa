import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SessionService } from '../../services/session/session.service';
import { Observable } from 'rxjs/Observable';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login$: Observable<any>;

  // Set form validation here
  form = new FormGroup({
    email: new FormControl("", [
      Validators.required,
      Validators.email]),
    password: new FormControl("", Validators.required)
  })

  // Error message
  message: string = "";

  constructor(private session: SessionService, private router: Router) { }

  ngOnInit() {

  }

  onSubmit() {
    let email = this.form.get("email").value;
    let password = this.form.get("password").value;

    this.session.login(email, password).subscribe(
      // On successful login, store user_id and user_type into localStorage.
      // Set the class variables.
      data => {
        // Set session into localstorage, reroute, and clear message.
        this.session.clearSession();
        this.session.setSession(data);
        this.router.navigateByUrl('reservation/history');
        this.message = "";
      },
      error => this.message = "Wrong Credentials"
    );
  }

}
