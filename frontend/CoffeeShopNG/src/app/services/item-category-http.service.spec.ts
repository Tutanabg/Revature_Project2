import { TestBed } from '@angular/core/testing';

import { ItemCategoryHttpService } from './item-category-http.service';

describe('ItemCategoryHttpService', () => {
  let service: ItemCategoryHttpService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ItemCategoryHttpService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
