import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnvironmentUploadComponent } from './environment-upload.component';

describe('OaUploadComponent', () => {
  let component: EnvironmentUploadComponent;
  let fixture: ComponentFixture<EnvironmentUploadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EnvironmentUploadComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(EnvironmentUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
