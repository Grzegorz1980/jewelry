import { TestBed, inject } from '@angular/core/testing';

import { JewelryListService } from './jewelry-list.service';

describe('JewelryListService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [JewelryListService]
    });
  });

  it('should be created', inject([JewelryListService], (service: JewelryListService) => {
    expect(service).toBeTruthy();
  }));
});
