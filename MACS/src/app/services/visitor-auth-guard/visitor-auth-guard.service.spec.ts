import { TestBed, inject } from '@angular/core/testing';

import { VisitorAuthGuardService } from './visitor-auth-guard.service';

describe('VisitorAuthGuardService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [VisitorAuthGuardService]
    });
  });

  it('should be created', inject([VisitorAuthGuardService], (service: VisitorAuthGuardService) => {
    expect(service).toBeTruthy();
  }));
});
