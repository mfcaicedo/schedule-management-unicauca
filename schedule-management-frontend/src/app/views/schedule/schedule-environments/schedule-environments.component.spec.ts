import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduleEnvironmentsComponent } from './schedule-environments.component';

describe('ScheduleEnvironmentsComponent', () => {
  let component: ScheduleEnvironmentsComponent;
  let fixture: ComponentFixture<ScheduleEnvironmentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScheduleEnvironmentsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScheduleEnvironmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
