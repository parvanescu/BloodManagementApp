import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllHospitalsComponent } from './get-all-hospitals.component';

describe('GetAllHospitalsComponent', () => {
  let component: GetAllHospitalsComponent;
  let fixture: ComponentFixture<GetAllHospitalsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetAllHospitalsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GetAllHospitalsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
