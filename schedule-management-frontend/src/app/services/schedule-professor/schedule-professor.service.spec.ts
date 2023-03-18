import { TestBed } from '@angular/core/testing';

import { ScheduleProfessorService } from './schedule-professor.service';

describe('ScheduleProfessorService', () => {
  let service: ScheduleProfessorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ScheduleProfessorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
