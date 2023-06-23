import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportEnvironmentComponent } from './report-environment.component';

describe('ReportEnvironmentComponent', () => {
  let component: ReportEnvironmentComponent;
  let fixture: ComponentFixture<ReportEnvironmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReportEnvironmentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReportEnvironmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
