import { Component, OnInit } from '@angular/core';
import { SessionService } from '../../services/session/session.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private session: SessionService) { }

  ngOnInit() {

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
