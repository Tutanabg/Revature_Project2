import { TestBed } from '@angular/core/testing';

import { OrderManagementHttpService } from './order-management-http.service';

describe('OrderManagementHttpService', () => {
  let service: OrderManagementHttpService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OrderManagementHttpService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
