import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeAccountDetailsComponent } from './change-account-details.component';

describe('ChangeAccountDetailsComponent', () => {
  let component: ChangeAccountDetailsComponent;
  let fixture: ComponentFixture<ChangeAccountDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChangeAccountDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeAccountDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
