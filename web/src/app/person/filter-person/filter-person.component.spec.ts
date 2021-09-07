import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilterPersonComponent } from './filter-person.component';

describe('FilterPersonComponent', () => {
  let component: FilterPersonComponent;
  let fixture: ComponentFixture<FilterPersonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FilterPersonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FilterPersonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
