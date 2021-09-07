import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetTransfusionComponent } from './get-transfusion.component';

describe('GetTransfusionComponent', () => {
  let component: GetTransfusionComponent;
  let fixture: ComponentFixture<GetTransfusionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetTransfusionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GetTransfusionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
