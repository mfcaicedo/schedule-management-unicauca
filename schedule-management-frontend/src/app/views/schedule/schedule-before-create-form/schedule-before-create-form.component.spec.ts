import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduleBeforeCreateFormComponent } from './schedule-before-create-form.component';

describe('ScheduleBeforeCreateFormComponent', () => {
  let component: ScheduleBeforeCreateFormComponent;
  let fixture: ComponentFixture<ScheduleBeforeCreateFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScheduleBeforeCreateFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScheduleBeforeCreateFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
