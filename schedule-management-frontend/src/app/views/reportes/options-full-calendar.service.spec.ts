import { TestBed } from '@angular/core/testing';

import { OptionsFullCalendarService } from './options-full-calendar.service';

describe('OptionsFullCalendarService', () => {
  let service: OptionsFullCalendarService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OptionsFullCalendarService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
