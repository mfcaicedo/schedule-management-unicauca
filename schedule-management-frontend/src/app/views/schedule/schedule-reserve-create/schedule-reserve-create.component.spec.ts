import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduleReserveCreateComponent } from './schedule-reserve-create.component';

describe('ScheduleReserveCreateComponent', () => {
  let component: ScheduleReserveCreateComponent;
  let fixture: ComponentFixture<ScheduleReserveCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScheduleReserveCreateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScheduleReserveCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
