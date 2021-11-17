package com.revature.models;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderID;

    @Column(name = "orderTime")
    private long orderTime;

    @ManyToOne
    @JoinColumn(name = "order_status")
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "ordered_by")
    private User orderedBy;

    @ManyToOne
    @JoinColumn(name = "order_payment")
    private PaymentType orderPayment;

    @Column(name = "delivery", columnDefinition = "boolean")
    private boolean delivery;

    public Order() {
    }

    public Order(int orderID, long orderTime, OrderStatus orderStatus, User orderedBy, PaymentType orderPayment, Boolean delivery) {
        this.orderID = orderID;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
        this.orderedBy = orderedBy;
        this.orderPayment = orderPayment;
        this.delivery = delivery;
    }

    public int getorderID() {
        return orderID;
    }

    public void setorderID(int orderID) {
        this.orderID = orderID;
    }

    public long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(long orderTime) {
        this.orderTime = orderTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(User orderedBy) {
        this.orderedBy = orderedBy;
    }

    public PaymentType getOrderPayment() {
        return orderPayment;
    }

    public void setOrderPayment(PaymentType orderPayment) {
        this.orderPayment = orderPayment;
    }

    public Boolean getDelivery() {
        return delivery;
    }

    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getorderID() == order.getorderID() && getOrderTime() == order.getOrderTime() && Objects.equals(getOrderStatus(), order.getOrderStatus()) && Objects.equals(getOrderedBy(), order.getOrderedBy()) && Objects.equals(getOrderPayment(), order.getOrderPayment()) && Objects.equals(getDelivery(), order.getDelivery());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getorderID(), getOrderTime(), getOrderStatus(), getOrderedBy(), getOrderPayment(), getDelivery());
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderID=" + orderID +
                ", orderTime=" + orderTime +
                ", orderStatus=" + orderStatus +
                ", orderedBy=" + orderedBy +
                ", orderPayment=" + orderPayment +
                ", delivery=" + delivery +
                '}';
    }
}
