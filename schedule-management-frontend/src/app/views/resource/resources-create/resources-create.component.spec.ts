import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResourcesCreateComponent } from './resources-create.component';

describe('ResourcesCreateComponent', () => {
  let component: ResourcesCreateComponent;
  let fixture: ComponentFixture<ResourcesCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResourcesCreateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResourcesCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
