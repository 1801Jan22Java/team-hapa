import { TestBed, inject } from '@angular/core/testing';

import { FillAccountDetailsService } from './fill-account-details.service';

describe('FillAccountDetailsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FillAccountDetailsService]
    });
  });

  it('should be created', inject([FillAccountDetailsService], (service: FillAccountDetailsService) => {
    expect(service).toBeTruthy();
  }));
});
