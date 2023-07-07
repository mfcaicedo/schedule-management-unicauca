import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnvironmentsFromResourceComponent } from './environments-from-resource.component';

describe('EnvironmentsFromResourceComponent', () => {
  let component: EnvironmentsFromResourceComponent;
  let fixture: ComponentFixture<EnvironmentsFromResourceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnvironmentsFromResourceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EnvironmentsFromResourceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
