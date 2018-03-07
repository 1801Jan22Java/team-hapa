import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AdminFeedbackService } from '../../services/admin-feedback/admin-feedback.service';
import { feedback } from '../../types/feedback';
import { Observable } from 'rxjs/Observable';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-admin-feedback',
  templateUrl: './admin-feedback.component.html',
  styleUrls: ['./admin-feedback.component.css']
})
export class AdminFeedbackComponent implements OnInit {

  constructor(private adminFeedback: AdminFeedbackService) { }

  public feedbackList: feedback[];

  ngOnInit() {
    this.adminFeedback.getFeedback().subscribe(
      data => {
        this.feedbackList = [];
        this.feedbackList = data;
      }
    )
  }

}
