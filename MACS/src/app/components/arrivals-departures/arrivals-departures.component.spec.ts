import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArrivalsDeparturesComponent } from './arrivals-departures.component';

describe('ArrivalsDeparturesComponent', () => {
  let component: ArrivalsDeparturesComponent;
  let fixture: ComponentFixture<ArrivalsDeparturesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ArrivalsDeparturesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArrivalsDeparturesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
