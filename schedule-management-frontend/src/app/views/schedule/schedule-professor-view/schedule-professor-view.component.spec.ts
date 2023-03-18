import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduleProfessorViewComponent } from './schedule-professor-view.component';

describe('ScheduleProfessorViewComponent', () => {
  let component: ScheduleProfessorViewComponent;
  let fixture: ComponentFixture<ScheduleProfessorViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScheduleProfessorViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScheduleProfessorViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
