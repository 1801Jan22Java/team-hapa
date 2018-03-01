import { Component, OnInit , OnDestroy} from '@angular/core';
import { FlightSearchService } from '../../services/flight-search/flight-search.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-flight-search-results',
  templateUrl: './flight-search-results.component.html',
  styleUrls: ['./flight-search-results.component.css']
})
export class FlightSearchResultsComponent implements OnInit {

  constructor(private service: FlightSearchService) { }

  public flights: any[];
  public sub : Subscription;

  ngOnInit() {
    this.sub = this.service.displayOpenFlights()
      .subscribe(data => this.flights=data, error => console.log(error));
    // this.check();
  }

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
