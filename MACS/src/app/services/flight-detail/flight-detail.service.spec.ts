import { TestBed, inject } from '@angular/core/testing';

import { FlightDetailService } from './flight-detail.service';

describe('FlightDetailService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FlightDetailService]
    });
  });

  it('should be created', inject([FlightDetailService], (service: FlightDetailService) => {
    expect(service).toBeTruthy();
  }));
});
