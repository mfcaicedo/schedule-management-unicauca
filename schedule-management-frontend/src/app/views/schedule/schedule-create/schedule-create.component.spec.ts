import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduleCreateComponent } from './schedule-create.component';

describe('ScheduleCreateComponent', () => {
  let component: ScheduleCreateComponent;
  let fixture: ComponentFixture<ScheduleCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScheduleCreateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScheduleCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
