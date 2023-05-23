import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeachersComponent } from './teacher-detail.component';
describe('EnvironmentsComponent', () => {
  let component: TeachersComponent;
  let fixture: ComponentFixture<TeachersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TeachersComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(TeachersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
