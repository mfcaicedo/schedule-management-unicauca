import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartamentoComponent } from './departamento.component';

describe('DepartamentoComponent', () => {
  let component: DepartamentoComponent;
  let fixture: ComponentFixture<DepartamentoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DepartamentoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DepartamentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
