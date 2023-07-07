import { TestBed } from '@angular/core/testing';

import { CloseSessionGuard } from './close-session.guard';

describe('CloseSessionGuard', () => {
  let guard: CloseSessionGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(CloseSessionGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
