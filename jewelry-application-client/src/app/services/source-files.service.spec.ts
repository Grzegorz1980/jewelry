import { TestBed, inject } from '@angular/core/testing';

import { SourceFilesService } from './source-files.service';

describe('SourceFilesService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SourceFilesService]
    });
  });

  it('should be created', inject([SourceFilesService], (service: SourceFilesService) => {
    expect(service).toBeTruthy();
  }));
});
