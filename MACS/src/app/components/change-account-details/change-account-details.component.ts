import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ChangeAccountDetailsService } from '../../services/change-account-details/change-account-details.service';
import { FillAccountDetailsService } from '../../services/fill-account-details/fill-account-details.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { updateinfo } from '../../types/updateinfo';
import { enduser } from '../../types/enduser';
import { Observable } from 'rxjs/Observable';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-change-account-details',
  templateUrl: './change-account-details.component.html',
  styleUrls: ['./change-account-details.component.css']
})
export class ChangeAccountDetailsComponent implements OnInit {

  public firstname: string = "";
  public lastname: string = "";
  public email: string = "";
  public newpassword: string = "";
  public answer1: string = "";
  public answer2: string = "";
  public answer3: string = "";

  public user: enduser;

  form = new FormGroup({firstname: new FormControl(""),
                      lastname: new FormControl(""),
                      email: new FormControl(""),
                      password: new FormControl(""),
                      answer1: new FormControl(""),
                      answer2: new FormControl(""),
                      answer3: new FormControl("")
                      });

  constructor(private changeAccountDetails: ChangeAccountDetailsService,
              private fillAccountDetails: FillAccountDetailsService) { }


  ngOnInit() {
    this.fillAccountDetails.autofill().subscribe(
      data => {
        this.firstname = data.firstname;
        this.lastname = data.lastname;
        this.email = data.email;
        this.answer1 = data.secretAnswer1;
        this.answer2 = data.secretAnswer2;
        this.answer3 = data.secretAnswer3;
      }
    );
  }

  onSubmit() {

    let firstname_ = "";
    let lastname_ = "";
    let email_ = "";
    let newpassword_ = "";
    let answer1_ = "";
    let answer2_ = "";
    let answer3_ = "";

    firstname_ = this.form.get("firstname").value;
    lastname_ = this.form.get("lastname").value;
    email_ = this.form.get("email").value;
    newpassword_ = this.form.get("password").value;
    answer1_ = this.form.get("answer1").value;
    answer2_ = this.form.get("answer2").value;
    answer3_ = this.form.get("answer3").value;

    console.log(firstname_);

    this.changeAccountDetails.update(firstname_, lastname_, email_, newpassword_, answer1_, answer2_, answer3_).subscribe(
      data => {
        this.firstname = data.firstname;
        this.lastname = data.lastname;
        this.email = data.email;
        this.answer1 = data.answer1;
        this.answer2 = data.answer2;
        this.answer3 = data.answer3;
        console.log(data);
      }
    );
  }
}
