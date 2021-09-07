import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransfusionComponent } from './transfusion.component';

describe('TransfusionComponent', () => {
  let component: TransfusionComponent;
  let fixture: ComponentFixture<TransfusionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TransfusionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TransfusionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
