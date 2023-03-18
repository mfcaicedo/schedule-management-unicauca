import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduleUpdateComponent } from './schedule-update.component';

describe('ScheduleUpdateComponent', () => {
  let component: ScheduleUpdateComponent;
  let fixture: ComponentFixture<ScheduleUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScheduleUpdateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScheduleUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
