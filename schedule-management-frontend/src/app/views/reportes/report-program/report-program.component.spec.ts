import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportProgramComponent } from './report-program.component';

describe('ReportProgramComponent', () => {
  let component: ReportProgramComponent;
  let fixture: ComponentFixture<ReportProgramComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReportProgramComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReportProgramComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
