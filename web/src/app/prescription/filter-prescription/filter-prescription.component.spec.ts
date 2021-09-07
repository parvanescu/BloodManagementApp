import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilterPrescriptionComponent } from './filter-prescription.component';

describe('FilterPrescriptionComponent', () => {
  let component: FilterPrescriptionComponent;
  let fixture: ComponentFixture<FilterPrescriptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FilterPrescriptionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FilterPrescriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
