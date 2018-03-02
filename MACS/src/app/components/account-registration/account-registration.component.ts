import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-account-registration',
  templateUrl: './account-registration.component.html',
  styleUrls: ['./account-registration.component.css']
})
export class AccountRegistrationComponent implements OnInit {

  constructor(private router: Router) {
 
  }

  form = new FormGroup({
    firstname: new FormControl("", Validators.required),
    lastname: new FormControl("", Validators.required),
    email: new FormControl("", Validators.required),
    username: new FormControl("", Validators.required),
    password: new FormControl("", Validators.required),
    confirmPassword: new FormControl("", Validators.required),
    q1: new FormControl("", Validators.required),
    q2: new FormControl("", Validators.required),
    q3: new FormControl("", Validators.required)
  })


  ngOnInit() {
  }

  onSubmit(): void {
    // console.log(this.form.get('date').value);
    
    this.router.navigateByUrl('');
  }

}
