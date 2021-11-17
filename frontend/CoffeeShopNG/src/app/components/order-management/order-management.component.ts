import { Component, OnInit } from '@angular/core';
import { Order } from '../../models/Order';
import { OrderItem } from '../../models/OrderItem';
import { OrderManagementHttpService } from '../../services/order-management-http.service';
import { IngredientOrderItemHttpService } from '../../services/ingredient-order-item-http.service';
import { IngredientOrderItem } from 'src/app/models/IngredientOrderItem';

@Component({
  selector: 'app-order-management',
  templateUrl: './order-management.component.html',
  styleUrls: ['./order-management.component.css']
})
export class OrderManagementComponent implements OnInit {

  constructor(private orderHttp: OrderManagementHttpService, private ingredientOrderItemHttp: IngredientOrderItemHttpService) { }

  ngOnInit(): void {
    this.getOrderToday();
    this.getOrderItems();
    this.getIngredients();
  }

  orderByDayList: Order[];
  orderList: Order[]; 
  orderItemsList: OrderItem[];
  day: Date;
  buttonDisplay: string = "";
  ingredientForOrderItems: IngredientOrderItem[];
  
  // get all orders then for each order, getOrderItems

  getIngredients(): void{
    this.ingredientOrderItemHttp.getAllIngredientOrderItems().subscribe(
      (response)=> {
        this.ingredientForOrderItems=response;
        console.log(this.ingredientForOrderItems);
      }
    );
  }
  advanceOrder(order: Order): void{
    this.orderHttp.advanceOrder(order).subscribe(
      (response) =>{
        if(order.delivery==true && order.orderStatus.status=="Ready"){
          this.buttonDisplay = "Order is Complete";
        }
        if(order.orderStatus.status == "Ready" && !order.delivery==true) {
          this.buttonDisplay = "Order is Complete";
        }
        if(order.orderStatus.status == 'Order Received'){
          this.buttonDisplay = "Order is being Delivered";
        }
        console.log(response);
        // maybe find the orer in the list then update the item in the list, or store user in app.ceomponent
        this.getOrderToday();
        // window.location.reload();
      }
    );
  }
  
  isDelivery(order: Order): boolean {
    if(order.delivery==true && order.orderStatus.status=="Ready") {
      this.buttonDisplay = "Order is picked up by deliveryman and is being Delivered";
      return true;
    }
    return false;
  }

  statusReady(order: Order): boolean{
    if(order.orderStatus.status == "Ready" && !order.delivery==true){
      this.buttonDisplay = "Order is Complete";
      return true;
    }
    return false;
  }

  statusReceived(order: Order): boolean {
    if(order.orderStatus.status == 'Order Received'){
      this.buttonDisplay = "Order is ready";
      return true;
    }
    return false;
  }

  getOrderToday(): void{
    this.orderHttp.getOrdersToday().subscribe(
      (response) => {
        console.log(response);
        this.orderList = response;
      }
    );
  }
  getOrderDay(): void {
    console.log(this.day);
    let realday = new Date(this.day)
    let dayms = realday.getTime();
    this.orderHttp.getOrdersDay(dayms).subscribe(
      (response) => {
        this.orderByDayList = response;
      }
    )

  }
  getOrderItems(): void{
    this.orderHttp.getOrderItems().subscribe(
      (response) => {
        console.log(response);
        this.orderItemsList = response;
      }
    )
  }
  orderOptionFormat(order: Order): boolean{
    return order.delivery;
  }
}
