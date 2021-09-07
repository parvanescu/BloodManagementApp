import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteDrugComponent } from './delete-drug.component';

describe('DeleteDrugComponent', () => {
  let component: DeleteDrugComponent;
  let fixture: ComponentFixture<DeleteDrugComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteDrugComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteDrugComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
