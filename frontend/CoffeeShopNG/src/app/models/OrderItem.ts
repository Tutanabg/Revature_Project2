import {Order} from "./Order"
import {MenuItem} from "./MenuItem"


export class OrderItem {

    orderItemID: number;
    orderID: Order;
    menuItem: MenuItem;
    itemCount: number;


  constructor(
    orderItemID: number, 
    order: Order, 
    menuItem: MenuItem, 
    itemCount: number
) {
    this.orderItemID = orderItemID
    this.orderID = order
    this.menuItem = menuItem
    this.itemCount = itemCount
  }


}