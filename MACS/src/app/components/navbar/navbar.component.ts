import { Component, OnInit } from '@angular/core';
import { SessionService } from '../../services/session/session.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  firstname: string = this.session.firstname;

  constructor(public session: SessionService) { }

  ngOnInit() {
  }

  logout() {
    this.session.clearSession();
  }

}
