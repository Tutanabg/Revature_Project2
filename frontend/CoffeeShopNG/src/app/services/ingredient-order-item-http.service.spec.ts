import { TestBed } from '@angular/core/testing';

import { IngredientOrderItemHttpService } from './ingredient-order-item-http.service';

describe('IngredientOrderItemHttpService', () => {
  let service: IngredientOrderItemHttpService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IngredientOrderItemHttpService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
