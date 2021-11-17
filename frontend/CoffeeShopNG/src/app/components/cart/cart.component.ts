import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { User } from '../../models/User';
import { IngredientOrderItem } from "src/app/models/IngredientOrderItem";
import { PaymentType } from 'src/app/models/PaymentType';

import { CartService } from 'src/app/services/cart.service';
import { IngredientOrderItemHttpService } from 'src/app/services/ingredient-order-item-http.service';
import { LoginService } from 'src/app/services/login.service';



@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  constructor(private router: Router,private ls: LoginService, private cs: CartService, private ioiHttp: IngredientOrderItemHttpService) { }


  
  ngOnInit(){

    this.getCartItems();
    this.getUser();
    this.calculateTotals();
    
  }

  cart: Array<Array<IngredientOrderItem>> = [];
  user: User;
  subtotals: Array<number> = [];
  total: number = 0;

  paymentType: number = 1;
  delivery: boolean = false;



  

  //init/update functions
  getCartItems() {

    this.cart = this.cs.cart;
  }

  getUser() {
    this.user = this.ls.currentUser;
  }

  generateSubtotals() {

    for (let cartItem of this.cart) {

      let subtotal = 0;

      subtotal += cartItem[0].orderItem.menuItem.itemPrice;

      for (let ingredientPack of cartItem) {
        if (ingredientPack.ingredient) {
          subtotal += (ingredientPack.ingredient.cost * ingredientPack.ingredientCount);
        } 
      }
      subtotal *= cartItem[0].orderItem.itemCount;

      if (this.subtotals[this.cart.indexOf(cartItem)]) {

        this.subtotals[this.cart.indexOf(cartItem)] = subtotal;

      } else {

        this.subtotals.push(subtotal);
      }
    }
  }

  getTotal(){
    this.total = 0;
    for (let subtotal of this.subtotals) {
      this.total += subtotal;
    }
  }

  calculateTotals() {
    this.generateSubtotals();
    this.getTotal();
  }

  

  //button functions

  increaseCount(cartItem: Array<IngredientOrderItem>) {

    for (let ingredientPack of cartItem) {

      ingredientPack.orderItem.itemCount ++;
    }
    this.calculateTotals();
  }

  decreaseCount(cartItem: Array<IngredientOrderItem>) {

    for (let ingredientPack of cartItem) {

      ingredientPack.orderItem.itemCount --;
    }
    this.calculateTotals();
  }

  removeFromCart(cartItem: Array<IngredientOrderItem>) {

    const index = this.cart.indexOf(cartItem);

    if (index > -1) {

      this.subtotals.splice(index, 1);
    }
    this.cs.removeCartItem(cartItem);

    this.calculateTotals();
  }

  submitOrder() {

    this.getUser();

    if (this.user) {

      for (let cartItem of this.cart) {

        for (let ingredientPack of cartItem) {

          ingredientPack.orderItem.orderID.delivery = this.delivery;
          ingredientPack.orderItem.orderID.orderPayment = new PaymentType(this.paymentType, "cash");
          ingredientPack.orderItem.orderID.orderTime = Date.now();
          ingredientPack.orderItem.orderID.orderStatus.statusID = 1;
          ingredientPack.orderItem.orderID.orderedBy = this.user;
        }
      }
      this.ioiHttp.submitOrder(this.cart).subscribe( returnValue => console.log(returnValue));

      this.cart = [];
      this.cs.emptyCart();

      this.router.navigate(['/landingpage']);

    }
  }
}
