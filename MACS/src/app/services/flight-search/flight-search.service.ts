import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { of } from 'rxjs/observable/of';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class FlightSearchService {

  constructor(private http: HttpClient) { }

  // Temporary storage for observable
  observable: Observable<any>;

  // Test using array
  array: String[] = ["flight number", "flight info", "flight fluff"];

  searchOpenFlights(date: String, destination: String){
    // return this.http.get(date + " " + destination);

    // Placeholder
    // return of(this.array);
    this.observable= this.http.get('/src/assets/httpTest.json');
  }

  displayOpenFlights():Observable<any>{
    return this.observable;
  }

}
