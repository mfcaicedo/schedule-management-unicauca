import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportSemestreComponent } from './report-semestre.component';

describe('ReportSemestreComponent', () => {
  let component: ReportSemestreComponent;
  let fixture: ComponentFixture<ReportSemestreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReportSemestreComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReportSemestreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
