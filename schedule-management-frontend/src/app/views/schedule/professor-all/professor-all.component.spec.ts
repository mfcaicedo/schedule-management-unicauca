import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessorAllComponent } from './professor-all.component';

describe('ProfessorAllComponent', () => {
  let component: ProfessorAllComponent;
  let fixture: ComponentFixture<ProfessorAllComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfessorAllComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfessorAllComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
