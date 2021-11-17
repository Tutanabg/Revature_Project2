import { Ingredient } from "./Ingredient";
import { OrderItem } from "./OrderItem";

export class IngredientOrderItem {
    ingredientOrderItemID: number;
    orderItem: OrderItem;
    ingredient: Ingredient;
    ingredientCount: number;


  constructor(
    ingredientOrderItemID: number, 
    orderItem: OrderItem, 
    ingredient: Ingredient, 
    ingredientCount: number
) {
    this.ingredientOrderItemID = ingredientOrderItemID
    this.orderItem = orderItem
    this.ingredient = ingredient
    this.ingredientCount = ingredientCount
  }
  

}