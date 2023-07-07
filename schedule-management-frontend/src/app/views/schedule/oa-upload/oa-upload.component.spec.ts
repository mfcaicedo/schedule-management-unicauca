import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OaUploadComponent } from './oa-upload.component';

describe('OaUploadComponent', () => {
  let component: OaUploadComponent;
  let fixture: ComponentFixture<OaUploadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OaUploadComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OaUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
