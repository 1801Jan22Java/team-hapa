import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';
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

  constructor(private http: HttpClient) { }

  // Keys for localStorage
  private user_id: string = "userID"
  private firstName: string = "firstName"
  private user_type: string = "user_type";

  public session$: Observable<auth>;
  public firstname: string;

  login(email: string, password: string): Observable<any> {
    let u: user = { email, password };
    const httpOptions = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*'
      })
    };
    // // Send a user object, and get an auth object array back.
    this.session$ = this.http.post<auth>('/MACSAirport/util/login', u, httpOptions);

    return this.session$;
    // // For local testing purposes
    // localStorage.setItem(this.user_id, email);
    // localStorage.setItem(this.user_type, password);
  }

  getSession(): Observable<auth> {
    return this.session$;
  }

  setSession(data) {
    if (data.firstName == null) {
      localStorage.setItem(this.user_id, "Profile");
    } else {
      // Set firstname whenever the session is set.
      // This is primarily for the navbar to display
      // the firstname.
      this.firstname = data.firstName
      localStorage.setItem(this.firstName, data.firstName);
    }

    if (data.userID == null) {
      localStorage.setItem(this.user_id, "0");
    } else {
      localStorage.setItem(this.user_id, data.userID.toString());
    }
    localStorage.setItem(this.user_type, data.type.refValue.toString());
  }

  clearSession() {
    localStorage.removeItem(this.user_id);
    localStorage.removeItem(this.firstName);
    localStorage.removeItem(this.user_type);
  }

  // Implementation specific code
  checkAdmin(): boolean {

    if (localStorage.getItem(this.user_type) == "Employee") {

      return true;
    }
    return false;
  }
  checkUser(): boolean {
    if (localStorage.getItem(this.user_type) == "Passenger") {
      return true;
    }
    return false;
  }

  checkLoggedIn(): boolean {
    if (localStorage.getItem(this.user_type) == "Employee" ||
      localStorage.getItem(this.user_type) == "Passenger") {
      return true;
    }
    return false;
  }

  getUserId(): number {
    return parseInt(localStorage.getItem(this.user_id));
  }
  getFirstName(): string {
    return localStorage.getItem(this.firstName);
  }
  getUserType(): string {
    return localStorage.getItem(this.user_type);
  }
}
