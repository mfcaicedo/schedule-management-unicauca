import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduleCreateFormComponent } from './schedule-create-form.component';

describe('ScheduleCreateFormComponent', () => {
  let component: ScheduleCreateFormComponent;
  let fixture: ComponentFixture<ScheduleCreateFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScheduleCreateFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScheduleCreateFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
