import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrackFlightStatusResultsComponent } from './track-flight-status-results.component';

describe('TrackFlightStatusResultsComponent', () => {
  let component: TrackFlightStatusResultsComponent;
  let fixture: ComponentFixture<TrackFlightStatusResultsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrackFlightStatusResultsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrackFlightStatusResultsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
