import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SubmitFeedbackService } from '../../services/submit-feedback/submit-feedback.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { updateinfo } from '../../types/updateinfo';
import { enduser } from '../../types/enduser';
import { Observable } from 'rxjs/Observable';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-submit-feedback',
  templateUrl: './submit-feedback.component.html',
  styleUrls: ['./submit-feedback.component.css']
})
export class SubmitFeedbackComponent implements OnInit {

  constructor(private submitFeedback: SubmitFeedbackService, private router: Router) { }

  form = new FormGroup({feedback: new FormControl("")});

  public feedback: string="";

  ngOnInit() {
  }

  onSubmit() {
    this.feedback = this.form.get("feedback").value;
    this.submitFeedback.submitFeedback(this.feedback).subscribe(
      data => {
        this.router.navigateByUrl("home");
      }
    )
  }

}
