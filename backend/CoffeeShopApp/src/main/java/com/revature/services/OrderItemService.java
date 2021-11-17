package com.revature.services;

import com.revature.models.OrderItem;

import java.text.ParseException;
import java.util.List;

public interface OrderItemService {
    public OrderItem addOrderItem(OrderItem o);
    public List<OrderItem> getAllOrderItems();
    public OrderItem getOrderItem(int id);
    public OrderItem updateOrderItem(OrderItem change);
    public boolean deleteOrderItem(int id);

    public List<OrderItem> getOrderItemsForToday(String todayDate) throws ParseException;
}
