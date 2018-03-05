import { Component, OnInit } from '@angular/core';
import { SessionService } from '../../services/session/session.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  constructor(public session : SessionService) { }

  ngOnInit() {
  }

}
