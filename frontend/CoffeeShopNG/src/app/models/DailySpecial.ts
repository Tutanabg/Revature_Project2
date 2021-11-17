import { MenuItem } from "./menuItem";
import { Shop } from "./Shop";

export class DailySpecial {
    dailySpecialID: number;
    shop: Shop;
    dayOfWeek: number;
    menuItem: MenuItem;


  constructor(
    dailySpecialID: number, 
    shop: Shop, 
    dayOfWeek: number, 
    menuItem: MenuItem
) {
    this.dailySpecialID = dailySpecialID
    this.shop = shop
    this.dayOfWeek = dayOfWeek
    this.menuItem = menuItem
  }

}