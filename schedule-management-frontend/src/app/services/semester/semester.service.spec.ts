import { TestBed } from '@angular/core/testing';

import { SemesterService } from './semester.service';

describe('SemesterService', () => {
  let service: SemesterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SemesterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
