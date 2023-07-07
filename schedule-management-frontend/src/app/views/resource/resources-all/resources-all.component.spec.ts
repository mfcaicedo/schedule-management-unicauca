import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResourcesAllComponent } from './resources-all.component';

describe('ResourcesAllComponent', () => {
  let component: ResourcesAllComponent;
  let fixture: ComponentFixture<ResourcesAllComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResourcesAllComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResourcesAllComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
