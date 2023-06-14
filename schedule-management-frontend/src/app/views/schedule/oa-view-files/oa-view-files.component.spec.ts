import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OaViewFilesComponent } from './oa-view-files.component';

describe('OaViewFilesComponent', () => {
  let component: OaViewFilesComponent;
  let fixture: ComponentFixture<OaViewFilesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [OaViewFilesComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(OaViewFilesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
