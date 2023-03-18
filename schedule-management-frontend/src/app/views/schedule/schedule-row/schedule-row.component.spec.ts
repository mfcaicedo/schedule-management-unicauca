import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduleRowComponent } from './schedule-row.component';

describe('ScheduleRowComponent', () => {
  let component: ScheduleRowComponent;
  let fixture: ComponentFixture<ScheduleRowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScheduleRowComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScheduleRowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
