import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrackFlightStatusComponent } from './track-flight-status.component';

describe('TrackFlightStatusComponent', () => {
  let component: TrackFlightStatusComponent;
  let fixture: ComponentFixture<TrackFlightStatusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrackFlightStatusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrackFlightStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
