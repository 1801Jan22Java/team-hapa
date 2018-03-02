import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import { auth } from '../../types/auth';
import { user } from '../../types/user';
import { Subscription } from 'rxjs';
import { of } from 'rxjs/observable/of';


@Injectable()
export class SessionService  {
  /*
  Keeps track of 2 session 'objects': the Observable and LocalStorage.

  The Observable is needed to change things on-demand, and can be 
  checked for changes for immediate results. 
  E.g. navbar will subscribe to the Observable object to change what
  it displays.

  The localStorage is used to keep track of sessions even when the
  window is closed. On instantiation of the service, there is a check
  for the auth object variables, user_id and user_type. This is then 
  placed back in the Observable, which makes all the changes possible. 
  */

  auth: Observable<auth[]>;

  // Check for session using the auth Observable first.
  // If nothing is found, check localStorage
  // If nothing is found, leave Observable as-is.
  constructor(private http: HttpClient) { }

  getAuth(): Observable<auth[]>{
    if(this.auth[0] == null){
      this.auth = of([this.getSession()]);
    }
    return this.auth;
  }

  setSession(username: string, password: string) {
    let u: user = { username, password };

    // Send a user object, and get an auth object array back.
    this.auth = this.http.post<auth[]>('util/login', u);

    // Login must subscribe in order to store the session in localStorage
    // Store in local storage asap.
    this.auth.subscribe(
      // On successful login, store user_id and user_type into localStorage
      data => {
        localStorage.setItem("user_id", data[0].user_id.toString());
        localStorage.setItem("user_type", data[0].user_type.toString());
      },
      error => console.log(error)
    );

    // For testing purposes
    localStorage.setItem("user_id", "1");
    localStorage.setItem("user_type", "1");
    this.auth = of([this.getSession()]);
    
  }

  // Retrieve object values from localStorage
  getSession(): auth {
    let user_id: number = parseInt(localStorage.getItem("user_id"));
    let user_type: number = parseInt(localStorage.getItem("user_type"));
    return { user_id, user_type };
  }

  clearSession() {
    this.auth = new Observable<auth[]>();

    localStorage.removeItem("user_id");
    localStorage.removeItem("user_type");
  }

}
