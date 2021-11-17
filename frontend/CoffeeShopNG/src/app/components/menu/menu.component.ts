import { Component, OnInit, ViewChild,ElementRef } from '@angular/core';
import { ItemCategory } from 'src/app/models/ItemCategory';
import { ItemCategoryHttpService } from 'src/app/services/item-category-http.service'
import { MenuItem } from "src/app/models/MenuItem";
import { MenuItemHttpService } from 'src/app/services/menu-item-http.service'
import { MenuItemIngredient } from "src/app/models/MenuItemIngredient";
import { MenuItemIngredientHttpService } from 'src/app/services/menu-item-ingredient-http.service'
import { CartService } from 'src/app/services/cart.service'
import { IngredientOrderItem } from 'src/app/models/IngredientOrderItem';
import { Ingredient } from 'src/app/models/Ingredient';
import { DailySpecial } from 'src/app/models/DailySpecial'
import { DailySpecialHttpService } from 'src/app/services/daily-special-http.service'



@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor(private icHttp: ItemCategoryHttpService, private miHttp: MenuItemHttpService, private miiHttp: MenuItemIngredientHttpService, private dsHttp: DailySpecialHttpService, private cs: CartService) { }

  ngOnInit(): void {

    this.getAllData();

  }

  itemCategories: Array<ItemCategory> = [];

  menuItems: Array<MenuItem> = [];

  menuItemIngredients: Array<MenuItemIngredient> = [];

  dailySpecials: Array<DailySpecial> = [];


  ingredients: Array<Ingredient> = [];

  selectedIngredients: Array<any> = [];

  today: number = 0;


  //user clicks addToCart ->

    //1.) create an array of ingredientOrderItems, where each element:
      //has the same menuItem
      //has a different ingredient and ingredientCount
      //this array represents an ordered item with all the details for extra items

    //2.) if that array already exists in the cart (an array of arrays)
      // increase the existing array's itemCount by 1
      // else add the array to the cart
  


  getAllData() {
    
    this.miHttp.getAllMenuItems().subscribe(

      (miResponse) => {
        this.menuItems = miResponse;
        //(this.menuItems);

        this.miiHttp.getAllMenuItemIngredients().subscribe(

          (miiResponse) => {
            this.menuItemIngredients = miiResponse;
            //(this.menuItemIngredients);

            this.icHttp.getAllItemCategories().subscribe(

              (icResponse) => {
                this.itemCategories = icResponse;
                //(this.itemCategories);

                this.getAllIngredients();
                //(this.ingredients);

                this.generateEmptySelectedIngredients();
                //(this.selectedIngredients);

                this.dsHttp.getAllDailySpecials().subscribe(

                  (dsResponse) => {
                    this.dailySpecials = dsResponse;
                    //(this.dailySpecials);

                    let d = new Date();
                    this.today = d.getDay();
                    //(this.today);

                    this.dailySpecialize();
                  }
                );
              }
            );

          }
        );

      }
    );
  }



  getAllIngredients() {

    for (let mui of this.menuItemIngredients) {

      let ingredient: Ingredient = mui.ingredient;

      let alreadyAdded: boolean = false;

      for (let ing of this.ingredients) {

        if (ing.ingredientID == ingredient.ingredientID) {
          alreadyAdded = true;
        }
      }
      if (!alreadyAdded) {
        
        this.ingredients.push(ingredient);
      }
    }
  }

  generateEmptySelectedIngredients() {

    for (let item of this.menuItems) {

      let outerElement = {
        item: item,
        ingredients: []
      };

    for (let ing of this.ingredients) {

      let innerElement = {
        ingredient: ing,
        ingredientCount: 0
      }
        outerElement.ingredients.push(innerElement);
    }
      this.selectedIngredients.push(outerElement);
    }
  }

  dailySpecialize() {

    for (let thing of this.menuItems) {
    
      if (this.dailySpecials[this.today].menuItem.itemID == thing.itemID) {

        thing.itemPrice *= 0.8;
      }
    }
  }



  updateSelectedIngredients(count: number, item: MenuItem, ingredient: Ingredient) {

    for (let outerElement of this.selectedIngredients) {

      if (outerElement.item.itemID == item.itemID) {

        for (let innerElement of outerElement.ingredients) {

          if (innerElement.ingredient.ingredientID == ingredient.ingredientID) {

            if (count > 2) {
              count = 2;
            } else if (count < 0) {
              count = 0;
            }

            innerElement.ingredientCount = count;
          }
        }
      }
    }
    //(this.selectedIngredients);
  }

  addToCart(item: MenuItem) {

    let noExtras: boolean = true;
    let cartItem: Array<IngredientOrderItem> = [];

    for (let ing of this.selectedIngredients[this.menuItems.indexOf(item)].ingredients) {

      if (ing.ingredientCount > 0) {

        let ioi: IngredientOrderItem = {
          ingredientOrderItemID: null,
          orderItem: {
            orderItemID: null,
            orderID: {
              orderID: null,
              orderTime: null,
              orderStatus:{
                statusID: null,
                status: null
              },
              orderedBy: null,
              orderPayment: null,
              delivery: null
            },
            menuItem: item, //needs a value
            itemCount: 1 //needs a value
          },
          ingredient: ing.ingredient, //needs a value
          ingredientCount: ing.ingredientCount //needs a value
        }

        cartItem.push(ioi);

        noExtras = false;
      }
    }

    if (noExtras) {

      let ioi: IngredientOrderItem = {
        ingredientOrderItemID: null,
        orderItem: {
          orderItemID: null,
          orderID: {
            orderID: null,
            orderTime: null,
            orderStatus:{
              statusID: null,
              status: null
            },
            orderedBy: null,
            orderPayment: null,
            delivery: null
          },
          menuItem: item, //needs a value
          itemCount: 1 //needs a value
        },
        ingredient: null, //needs a value
        ingredientCount: null //needs a value
      }

      cartItem.push(ioi);

    }

    this.cs.addCartItem(cartItem);
  }

}
