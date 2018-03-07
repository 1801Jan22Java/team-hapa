import { Component, OnInit } from '@angular/core';
import { enduser } from '../../types/enduser';
import { Observable } from 'rxjs/Observable';
import { Subscription } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-users',
  templateUrl: './admin-users.component.html',
  styleUrls: ['./admin-users.component.css']
})
export class AdminUsersComponent implements OnInit {

  constructor(
    private http: HttpClient,
    private router: Router
  ) { }

  public users: any[];
  public sub : Subscription;
  public aUser : enduser;

  ngOnInit() {
    this.http.post<any>(
      'http://localhost:8080/MACSAirport/util/admin/users',
      '').subscribe(
        data=>{
        this.users=data;
        console.log(this.users);
      });
  }

  noFly(thisUser) {

    this.http.post<enduser>('http://localhost:8080/MACSAirport/util/admin/nofly', { id : thisUser.id }).subscribe(
      data => thisUser.noFly = true
    );
  }

}
