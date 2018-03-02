import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import { auth } from '../../types/auth';
import { user } from '../../types/user';
import { Subscription } from 'rxjs';
import { of } from 'rxjs/observable/of';


@Injectable()
export class SessionService {
  /*
  Keeps track of 2 session 'objects': the session variables and 
  LocalStorage.

  The session variables are needed to change things on-demand, and 
  can be checked for changes for immediate results. They are created
  when the Observable returns, and can be seen by various components.
  e.g. the navbar component will have the session's user_type 
  available. When it changes, it will be seen and used in an *ngIf
  or *ngSwitch to display and hide certain parts.

  The localStorage is used to keep track of sessions even when the
  window is closed. On instantiation of the service, there is a check
  for the auth object variables, user_id and user_type. This is then 
  placed back in the Observable, which makes all the changes possible. 
  */

  public user_id: number;
  public user_type: number;

  constructor(private http: HttpClient) { }

  setSession(username: string, password: string) {
    // let u: user = { username, password };

    // // Send a user object, and get an auth object array back.
    // this.http.post<auth[]>('util/login', u).subscribe(
    //   // On successful login, store user_id and user_type into localStorage.
    //   // Set the class variables.
    //   data => {
    //     localStorage.setItem("user_id", data[0].user_id.toString());
    //     localStorage.setItem("user_type", data[0].user_type.toString());
    //     this.user_id = data[0].user_id;
    //     this.user_type = data[0].user_type;
    //   },
    //   error => console.log(error)
    // );

    // For testing purposes
    localStorage.setItem("user_id", username);
    localStorage.setItem("user_type", password);
  }

  getSession(): auth {
    let user_id = parseInt(localStorage.getItem("user_id"))
    let user_type = parseInt(localStorage.getItem("user_type"))
    return { user_id, user_type }
  }

  clearSession() {
    localStorage.removeItem("user_id");
    localStorage.removeItem("user_type");
    this.user_id = 0;
    this.user_type = 0;
  }

  // Implementation specific code
  checkAdmin():boolean {
    if (parseInt(localStorage.getItem("user_type"))==2){
      return true;
    }
    return false;
  }
  checkUser():boolean {
    if (parseInt(localStorage.getItem("user_type"))==1){
      return true;
    }
    return false;
  }
  
  checkLoggedIn(): boolean{
    if (parseInt(localStorage.getItem("user_type"))>=1 &&parseInt(localStorage.getItem("user_type")) !=undefined ){
      return true;
    }
    return false;
  }

  getUserId(): number {
    return parseInt(localStorage.getItem("user_id"));
  }
}
