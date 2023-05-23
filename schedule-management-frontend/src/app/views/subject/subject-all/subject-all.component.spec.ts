import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubjectAllComponent } from './subject-all.component';

describe('SubjectAllComponent', () => {
  let component: SubjectAllComponent;
  let fixture: ComponentFixture<SubjectAllComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubjectAllComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubjectAllComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
