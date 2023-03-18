import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduleTimeAllComponent } from './schedule-time-all.component';

describe('ScheduleTimeAllComponent', () => {
  let component: ScheduleTimeAllComponent;
  let fixture: ComponentFixture<ScheduleTimeAllComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScheduleTimeAllComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScheduleTimeAllComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
