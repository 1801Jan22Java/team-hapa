import { TestBed, inject } from '@angular/core/testing';

import { FlightDetailsService } from './flight-details.service';

describe('FlightDetailsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FlightDetailsService]
    });
  });

  it('should be created', inject([FlightDetailsService], (service: FlightDetailsService) => {
    expect(service).toBeTruthy();
  }));
});
