import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateTransfusionComponent } from './update-transfusion.component';

describe('UpdateTransfusionComponent', () => {
  let component: UpdateTransfusionComponent;
  let fixture: ComponentFixture<UpdateTransfusionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateTransfusionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateTransfusionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
