import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteTransfusionComponent } from './delete-transfusion.component';

describe('DeleteTransfusionComponent', () => {
  let component: DeleteTransfusionComponent;
  let fixture: ComponentFixture<DeleteTransfusionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteTransfusionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteTransfusionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
