import { TestBed } from '@angular/core/testing';

import { CreateNewAccountService } from './create-new-account.service';

describe('CreateNewAccountService', () => {
  let service: CreateNewAccountService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateNewAccountService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
