import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetHospitalComponent } from './get-hospital.component';

describe('GetHospitalComponent', () => {
  let component: GetHospitalComponent;
  let fixture: ComponentFixture<GetHospitalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetHospitalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GetHospitalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
