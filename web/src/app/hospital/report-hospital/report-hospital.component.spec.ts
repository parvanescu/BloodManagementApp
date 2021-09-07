import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportHospitalComponent } from './report-hospital.component';

describe('ReportHospitalComponent', () => {
  let component: ReportHospitalComponent;
  let fixture: ComponentFixture<ReportHospitalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReportHospitalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportHospitalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
