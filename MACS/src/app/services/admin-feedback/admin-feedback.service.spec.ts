import { TestBed, inject } from '@angular/core/testing';

import { AdminFeedbackService } from './admin-feedback.service';

describe('AdminFeedbackService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AdminFeedbackService]
    });
  });

  it('should be created', inject([AdminFeedbackService], (service: AdminFeedbackService) => {
    expect(service).toBeTruthy();
  }));
});
