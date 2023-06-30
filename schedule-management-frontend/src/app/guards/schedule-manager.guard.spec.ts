import { TestBed } from '@angular/core/testing';

import { ScheduleManagerGuard } from './schedule-manager.guard';

describe('ScheduleManagerGuard', () => {
  let guard: ScheduleManagerGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(ScheduleManagerGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
