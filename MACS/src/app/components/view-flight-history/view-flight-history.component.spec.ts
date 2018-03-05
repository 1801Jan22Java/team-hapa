import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewFlightHistoryComponent } from './view-flight-history.component';

describe('ViewFlightHistoryComponent', () => {
  let component: ViewFlightHistoryComponent;
  let fixture: ComponentFixture<ViewFlightHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewFlightHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewFlightHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
