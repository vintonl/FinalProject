import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResvCrudComponent } from './resv-crud.component';

describe('ResvCrudComponent', () => {
  let component: ResvCrudComponent;
  let fixture: ComponentFixture<ResvCrudComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResvCrudComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResvCrudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
