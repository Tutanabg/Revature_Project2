import { TestBed } from '@angular/core/testing';

import { MenuItemIngredientHttpService } from './menu-item-ingredient-http.service';

describe('MenuItemIngredientHttpService', () => {
  let service: MenuItemIngredientHttpService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MenuItemIngredientHttpService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
