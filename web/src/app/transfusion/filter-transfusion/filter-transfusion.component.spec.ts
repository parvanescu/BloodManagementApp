import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilterTransfusionComponent } from './filter-transfusion.component';

describe('FilterTransfusionComponent', () => {
  let component: FilterTransfusionComponent;
  let fixture: ComponentFixture<FilterTransfusionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FilterTransfusionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FilterTransfusionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
