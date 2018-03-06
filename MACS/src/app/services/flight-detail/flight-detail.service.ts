import { Injectable } from '@angular/core';
import { flight } from '../../types/flight';

@Injectable()
export class FlightDetailService {

  // Take in a flight object to display in the 
  // flight-details component
  flight: flight;

  constructor() { }

  setFlightDetails(flightObject: flight){
    this.flight = flightObject;
  }

  getFlightDetails(): flight{
    return this.flight;
  }

}
