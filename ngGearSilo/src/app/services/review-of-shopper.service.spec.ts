import { TestBed } from '@angular/core/testing';

import { ReviewOfShopperService } from './review-of-shopper.service';

describe('ReviewOfShopperService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ReviewOfShopperService = TestBed.get(ReviewOfShopperService);
    expect(service).toBeTruthy();
  });
});
