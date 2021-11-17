import { Injectable } from '@angular/core';
import { IngredientOrderItem } from "src/app/models/IngredientOrderItem";


@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor() { }


  cart: Array<Array<IngredientOrderItem>> = [];

  addCartItem(newIoiArray: Array<IngredientOrderItem>) {

    let alreadyInCart: boolean = false;

    for (let existingIoiArray of this.cart) {

      if (newIoiArray[0].orderItem.menuItem.itemID == existingIoiArray[0].orderItem.menuItem.itemID 
        && !newIoiArray[0].ingredient && !existingIoiArray[0].ingredient) { //if the new cart item and old cart item both don't have extra ingredients and have the same menu item

          for (let ioi of existingIoiArray) {

            ioi.orderItem.itemCount ++;
            alreadyInCart = true;
          }

        } else if (newIoiArray[0].orderItem.menuItem.itemID == existingIoiArray[0].orderItem.menuItem.itemID
        && newIoiArray.length == existingIoiArray.length
        && newIoiArray[0].ingredient && existingIoiArray[0].ingredient) { //both have ingredients and are the same length and have same menuItem

          for (let i = 0; i < newIoiArray.length; i++) {

            if (newIoiArray[i].ingredient.ingredientID == existingIoiArray[i].ingredient.ingredientID
              && newIoiArray[i].ingredientCount == existingIoiArray[i].ingredientCount) { //and all the ingredients are equal in type and quantity

              for (let ioi of existingIoiArray) {

                ioi.orderItem.itemCount ++;
                alreadyInCart = true;
              }
            }
          }
        }
      }
    if (!alreadyInCart) {

      this.cart.push(newIoiArray);
    }

    console.log(this.cart);
  }

  removeCartItem(ioiArray: Array<IngredientOrderItem>) {

    const index = this.cart.indexOf(ioiArray);
    if (index > -1) {
      this.cart.splice(index, 1);
    }

  }

  emptyCart() {
    this.cart = [];
  }

}
