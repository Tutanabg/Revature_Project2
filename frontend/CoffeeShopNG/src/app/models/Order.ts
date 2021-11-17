import { OrderStatus } from "./OrderStatus";
import { PaymentType } from "./PaymentType";
import { User } from "./User";

export class Order {
    orderID: number;
    orderTime: number;
    orderStatus: OrderStatus;
    orderedBy: User;
    orderPayment: PaymentType;
    delivery: boolean;

    constructor(orderID: number, orderTime: number, orderStatus: OrderStatus, orderedBy: User, orderPayment: PaymentType, delivery: boolean){

        this.orderID = orderID;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
        this.orderedBy = orderedBy;
        this.orderPayment = orderPayment;
        this.delivery = delivery;

    }
}

