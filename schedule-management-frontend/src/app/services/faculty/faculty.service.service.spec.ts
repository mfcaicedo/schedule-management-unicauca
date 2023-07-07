import { TestBed } from '@angular/core/testing';
import { FacultyService } from './faculty.service';


describe('FacultyServiceService', () => {
  let service: FacultyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FacultyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
