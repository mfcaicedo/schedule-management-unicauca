import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnvironmentDeleteComponent } from './environment-delete.component';

describe('EnvironmentDetailComponent', () => {
  let component: EnvironmentDeleteComponent;
  let fixture: ComponentFixture<EnvironmentDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnvironmentDeleteComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EnvironmentDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
