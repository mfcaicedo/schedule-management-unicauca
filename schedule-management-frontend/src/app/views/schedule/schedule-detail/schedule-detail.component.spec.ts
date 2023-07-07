import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduleDetailComponent } from './schedule-detail.component';

describe('ScheduleDetailComponent', () => {
  let component: ScheduleDetailComponent;
  let fixture: ComponentFixture<ScheduleDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScheduleDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScheduleDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
