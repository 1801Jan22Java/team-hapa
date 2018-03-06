import { TestBed, inject } from '@angular/core/testing';

import { FlightHistoryService } from './flight-history.service';

describe('FlightHistoryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FlightHistoryService]
    });
  });

  it('should be created', inject([FlightHistoryService], (service: FlightHistoryService) => {
    expect(service).toBeTruthy();
  }));
});
