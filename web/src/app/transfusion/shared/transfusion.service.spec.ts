import { TestBed } from '@angular/core/testing';

import { TransfusionService } from './transfusion.service';

describe('TransfusionService', () => {
  let service: TransfusionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TransfusionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
