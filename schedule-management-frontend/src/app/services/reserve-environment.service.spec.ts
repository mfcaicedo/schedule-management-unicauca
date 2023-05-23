import { TestBed } from '@angular/core/testing';

import { ReserveEnvironmentService } from './reserve-environment.service';

describe('ReserveEnvironmentService', () => {
  let service: ReserveEnvironmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReserveEnvironmentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
