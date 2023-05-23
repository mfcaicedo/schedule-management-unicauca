import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubjectUploadComponent } from './subject-upload.component';

describe('OaUploadComponent', () => {
  let component: SubjectUploadComponent;
  let fixture: ComponentFixture<SubjectUploadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SubjectUploadComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(SubjectUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
