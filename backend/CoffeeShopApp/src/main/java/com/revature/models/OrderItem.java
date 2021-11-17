package com.revature.models;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id", updatable = false)
    private int orderItemID;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orderID;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private MenuItem menuItem;

    @Column(name = "item_count")
    private int  itemCount;

    public OrderItem() {
    }
    public OrderItem(int orderItemID, Order orderID, MenuItem menuItem, int itemCount) {
        this.orderItemID = orderItemID;

        this.orderID = orderID;
        this.menuItem = menuItem;
        this.itemCount = itemCount;
    }

    public OrderItem(Order orderID, MenuItem menuItem, int itemCount) {
        this.orderID = orderID;
        this.menuItem = menuItem;
        this.itemCount = itemCount;
    }

    public int getOrderItemID() {
        return orderItemID;
    }

    public void setOrderItemID(int orderItemID) {
        this.orderItemID = orderItemID;
    }

    public Order getOrderID() {
        return orderID;
    }

    public void setOrderID(Order orderID) {
        this.orderID = orderID;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;
        OrderItem orderItem = (OrderItem) o;
        return getOrderItemID() == orderItem.getOrderItemID() && getItemCount() == orderItem.getItemCount() && Objects.equals(getOrderID(), orderItem.getOrderID()) && Objects.equals(getMenuItem(), orderItem.getMenuItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderItemID(), getOrderID(), getMenuItem(), getItemCount());
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemID=" + orderItemID +
                ", orderID=" + orderID +
                ", menuItem=" + menuItem +
                ", itemCount=" + itemCount +
                '}';
    }
}
