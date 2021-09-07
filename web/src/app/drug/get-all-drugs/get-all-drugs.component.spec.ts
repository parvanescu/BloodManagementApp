import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllDrugsComponent } from './get-all-drugs.component';

describe('GetAllDrugsComponent', () => {
  let component: GetAllDrugsComponent;
  let fixture: ComponentFixture<GetAllDrugsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetAllDrugsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GetAllDrugsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
