import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetDrugComponent } from './get-drug.component';

describe('GetDrugComponent', () => {
  let component: GetDrugComponent;
  let fixture: ComponentFixture<GetDrugComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetDrugComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GetDrugComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
