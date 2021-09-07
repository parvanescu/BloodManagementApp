import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletePrescriptionComponent } from './delete-prescription.component';

describe('DeletePrescriptionComponent', () => {
  let component: DeletePrescriptionComponent;
  let fixture: ComponentFixture<DeletePrescriptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeletePrescriptionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeletePrescriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
