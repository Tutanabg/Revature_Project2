import { Ingredient } from "./Ingredient";
import { MenuItem } from "./menuItem";

export class MenuItemIngredient {
    menuItemIngredientID: number;
    menuItem: MenuItem;
    ingredient: Ingredient;



  constructor(menuItemIngredientID: number, item: MenuItem, ingredient: Ingredient) {
    this.menuItemIngredientID = menuItemIngredientID;
    this.menuItem = item;
    this.ingredient = ingredient;
  }
  

}