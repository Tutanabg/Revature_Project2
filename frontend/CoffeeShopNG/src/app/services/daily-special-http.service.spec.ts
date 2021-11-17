import { TestBed } from '@angular/core/testing';

import { DailySpecialHttpService } from './daily-special-http.service';

describe('DailySpecialHttpService', () => {
  let service: DailySpecialHttpService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DailySpecialHttpService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
