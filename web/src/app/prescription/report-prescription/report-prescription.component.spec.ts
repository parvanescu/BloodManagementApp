import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportPrescriptionComponent } from './report-prescription.component';

describe('ReportPrescriptionComponent', () => {
  let component: ReportPrescriptionComponent;
  let fixture: ComponentFixture<ReportPrescriptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReportPrescriptionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportPrescriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
