import { TestBed } from '@angular/core/testing';

import { OpenSesionGuard } from './open-sesion.guard';

describe('OpenSesionGuard', () => {
  let guard: OpenSesionGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(OpenSesionGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
