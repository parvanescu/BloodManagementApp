import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilterDrugComponent } from './filter-drug.component';

describe('FilterDrugComponent', () => {
  let component: FilterDrugComponent;
  let fixture: ComponentFixture<FilterDrugComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FilterDrugComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FilterDrugComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
