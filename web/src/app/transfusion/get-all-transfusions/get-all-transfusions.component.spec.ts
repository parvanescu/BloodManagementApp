import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllTransfusionsComponent } from './get-all-transfusions.component';

describe('GetAllTransfusionsComponent', () => {
  let component: GetAllTransfusionsComponent;
  let fixture: ComponentFixture<GetAllTransfusionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetAllTransfusionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GetAllTransfusionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
