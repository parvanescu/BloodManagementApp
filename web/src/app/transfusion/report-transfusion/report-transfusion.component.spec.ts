import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportTransfusionComponent } from './report-transfusion.component';

describe('ReportTransfusionComponent', () => {
  let component: ReportTransfusionComponent;
  let fixture: ComponentFixture<ReportTransfusionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReportTransfusionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportTransfusionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
