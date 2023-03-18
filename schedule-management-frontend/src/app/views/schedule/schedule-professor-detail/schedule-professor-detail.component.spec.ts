import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduleProfessorDetailComponent } from './schedule-professor-detail.component';

describe('ScheduleProfessorDetailComponent', () => {
  let component: ScheduleProfessorDetailComponent;
  let fixture: ComponentFixture<ScheduleProfessorDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScheduleProfessorDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScheduleProfessorDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
