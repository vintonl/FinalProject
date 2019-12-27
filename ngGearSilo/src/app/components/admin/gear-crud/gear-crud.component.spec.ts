import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GearCrudComponent } from './gear-crud.component';

describe('GearCrudComponent', () => {
  let component: GearCrudComponent;
  let fixture: ComponentFixture<GearCrudComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GearCrudComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GearCrudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
