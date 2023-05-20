import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduleReserveComponent } from './schedule-reserve.component';

describe('ScheduleReserveComponent', () => {
  let component: ScheduleReserveComponent;
  let fixture: ComponentFixture<ScheduleReserveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScheduleReserveComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScheduleReserveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
