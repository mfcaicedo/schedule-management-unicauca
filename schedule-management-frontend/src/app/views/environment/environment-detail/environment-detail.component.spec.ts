import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnvironmentDetailComponent } from './environment-detail.component';

describe('EnvironmentDetailComponent', () => {
  let component: EnvironmentDetailComponent;
  let fixture: ComponentFixture<EnvironmentDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnvironmentDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EnvironmentDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
