import { TestBed, inject } from '@angular/core/testing';

import { SubmitFeedbackService } from './submit-feedback.service';

describe('SubmitFeedbackService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SubmitFeedbackService]
    });
  });

  it('should be created', inject([SubmitFeedbackService], (service: SubmitFeedbackService) => {
    expect(service).toBeTruthy();
  }));
});
