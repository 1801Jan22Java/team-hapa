import { TestBed, inject } from '@angular/core/testing';

import { ChangeAccountDetailsService } from './change-account-details.service';

describe('ChangeAccountDetailsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ChangeAccountDetailsService]
    });
  });

  it('should be created', inject([ChangeAccountDetailsService], (service: ChangeAccountDetailsService) => {
    expect(service).toBeTruthy();
  }));
});
