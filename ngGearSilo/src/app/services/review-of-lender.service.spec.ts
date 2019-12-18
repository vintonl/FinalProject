import { TestBed } from '@angular/core/testing';

import { ReviewOfLenderService } from './review-of-lender.service';

describe('ReviewOfLenderService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ReviewOfLenderService = TestBed.get(ReviewOfLenderService);
    expect(service).toBeTruthy();
  });
});
