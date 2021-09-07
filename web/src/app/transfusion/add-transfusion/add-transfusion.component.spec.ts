import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTransfusionComponent } from './add-transfusion.component';

describe('AddTransfusionComponent', () => {
  let component: AddTransfusionComponent;
  let fixture: ComponentFixture<AddTransfusionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddTransfusionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddTransfusionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
