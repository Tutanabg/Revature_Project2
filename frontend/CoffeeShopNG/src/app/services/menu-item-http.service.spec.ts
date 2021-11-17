import { TestBed } from '@angular/core/testing';

import { MenuItemHttpService } from './menu-item-http.service';

describe('MenuItemHttpService', () => {
  let service: MenuItemHttpService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MenuItemHttpService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
