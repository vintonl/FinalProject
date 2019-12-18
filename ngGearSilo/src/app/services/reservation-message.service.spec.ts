import { TestBed } from '@angular/core/testing';

import { ReservationMessageService } from './reservation-message.service';

describe('ReservationMessageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ReservationMessageService = TestBed.get(ReservationMessageService);
    expect(service).toBeTruthy();
  });
});
