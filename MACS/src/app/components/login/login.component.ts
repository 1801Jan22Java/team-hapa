import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SessionService } from '../../services/session/session.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  // Set form validation here
  form = new FormGroup({
    email: new FormControl("", Validators.required),
    password: new FormControl("", Validators.required)
  })

  constructor(private session: SessionService, private router: Router) { }

  ngOnInit() {

  }

  onSubmit() {
    let email = this.form.get("email").value;
    let password = this.form.get("password").value;

    this.session.setSession(email, password);

    if (this.session.checkUser()) {
      this.router.navigateByUrl('reservation/history');
    } else {
      this.router.navigateByUrl('home');
    }
  }
  // this.check();

  // To allow only one result.
  // check(){
  //   let keepGoing =true;
  //   while(keepGoing){
  //     if(this.flights.length>0){
  //       this.sub.unsubscribe();
  //       keepGoing=false;
  //     }
  //   }
  // }

}
