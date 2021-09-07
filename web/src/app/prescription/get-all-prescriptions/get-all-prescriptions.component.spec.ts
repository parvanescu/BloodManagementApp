import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllPrescriptionsComponent } from './get-all-prescriptions.component';

describe('GetAllPrescriptionsComponent', () => {
  let component: GetAllPrescriptionsComponent;
  let fixture: ComponentFixture<GetAllPrescriptionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetAllPrescriptionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GetAllPrescriptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
