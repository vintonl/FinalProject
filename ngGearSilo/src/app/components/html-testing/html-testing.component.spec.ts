import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HtmlTestingComponent } from './html-testing.component';

describe('HtmlTestingComponent', () => {
  let component: HtmlTestingComponent;
  let fixture: ComponentFixture<HtmlTestingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HtmlTestingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HtmlTestingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
