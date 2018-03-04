import { Component, OnInit } from '@angular/core';
import { SessionService } from '../../services/session/session.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  firstname: string;

  constructor(public session: SessionService) { }

  ngOnInit() {
    this.sessionSubscribe()
  }

  // The function exists to allow session.service to be defined outside
  // of the ngOnInit. Otherwise, ngOnInit is called before the
  // session.service is instantiated. 
  sessionSubscribe(){
    this.session.getSession().subscribe(data=>
      {
        this.firstname = data.firstName;
      })
  }

  logout(){
    this.session.clearSession();
  } 

}
