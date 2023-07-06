import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduleReserveConsultComponent } from './schedule-reserve-consult.component';

describe('ScheduleReserveConsultComponent', () => {
  let component: ScheduleReserveConsultComponent;
  let fixture: ComponentFixture<ScheduleReserveConsultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScheduleReserveConsultComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScheduleReserveConsultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
