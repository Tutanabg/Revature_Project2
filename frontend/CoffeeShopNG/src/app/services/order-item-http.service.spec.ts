import { TestBed } from '@angular/core/testing';

import { OrderItemHttpService } from './order-item-http.service';

describe('OrderItemHttpService', () => {
  let service: OrderItemHttpService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OrderItemHttpService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
