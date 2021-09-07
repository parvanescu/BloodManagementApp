import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportDrugComponent } from './report-drug.component';

describe('ReportDrugComponent', () => {
  let component: ReportDrugComponent;
  let fixture: ComponentFixture<ReportDrugComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReportDrugComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportDrugComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
