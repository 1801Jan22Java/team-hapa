import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { auth } from '../../types/auth';
import { passwordreset } from '../../types/passwordreset';
import { ResetPasswordService } from '../../services/reset-password/reset-password.service';
import { Observable } from 'rxjs/Observable';
import { Subscription } from 'rxjs';
import { SessionService } from '../../services/session/session.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  constructor(private service : ResetPasswordService, private session : SessionService, private router : Router) { }

  form = new FormGroup({email: new FormControl(""),
                      password: new FormControl(""),
                      confirmPassword: new FormControl(""),
                      answer1: new FormControl(""),
                      answer2: new FormControl(""),
                      answer3: new FormControl("")
                      });

  public message: string = "";
  public passwordErrorMessage: string = "";
  
  // Keys for localStorage
  private user_id: string = "userID"
  private firstName: string = "firstName"
  private user_type: string = "user_type";                      

  ngOnInit() {
  }

  onSubmit() {
    let email = this.form.get("email").value;
    let newpassword = this.form.get("password").value;
    let answer1 = this.form.get("answer1").value;
    let answer2 = this.form.get("answer2").value;
    let answer3 = this.form.get("answer3").value;
    let passwordInfo: passwordreset = { email, answer1, answer2, answer3, newpassword };
    if(newpassword == this.form.get("confirmPassword").value && newpassword != ""){
      this.service.updatePassword(passwordInfo).subscribe(
        data => {
          this.session.setSession(data);
          this.router.navigateByUrl('reservation/history');
        },
        error => {
          this.message = "Answer(s) were incorrect!";
        }
      )
    } else if (newpassword == "") {
      this.passwordErrorMessage = "Please enter a password."
    } else {
      this.passwordErrorMessage = "Passwords do not match."
    }
  }
}
