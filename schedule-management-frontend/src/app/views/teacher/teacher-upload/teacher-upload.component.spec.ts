import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherUploadComponent } from './teacher-upload.component';

describe('OaUploadComponent', () => {
  let component: TeacherUploadComponent;
  let fixture: ComponentFixture<TeacherUploadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TeacherUploadComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(TeacherUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
