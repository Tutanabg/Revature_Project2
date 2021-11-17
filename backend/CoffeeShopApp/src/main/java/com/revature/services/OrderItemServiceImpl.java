package com.revature.services;

import com.revature.models.OrderItem;
import com.revature.repositories.OrderItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderItemServiceImpl implements OrderItemService{

    @Autowired
    OrderItemRepo oi;

    @Override
    public OrderItem addOrderItem(OrderItem o) {
        return oi.save(o);
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return (List<OrderItem>) oi.findAll();
    }

    @Override
    public OrderItem getOrderItem(int id) {
        return oi.findById(id).get();
    }

    @Override
    public OrderItem updateOrderItem(OrderItem change) {
        return oi.save(change);
    }

    @Override
    public boolean deleteOrderItem(int id) {
        try{
            oi.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<OrderItem> getOrderItemsForToday(String todayDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date today = format.parse(todayDate);
            System.out.println(todayDate);
            System.out.println(today);
            long todayTime = today.getTime();
            List<OrderItem> orderItems = (List<OrderItem>) oi.findAll();

            List<OrderItem> todayOrderItems = new ArrayList<>();
            for (OrderItem o : orderItems){
                if (o.getOrderID().getOrderTime()>=todayTime && o.getOrderID().getOrderTime()<todayTime+86400000L){
                    todayOrderItems.add(o);
                }
            }
            return todayOrderItems;
        } catch (ParseException p) {
            p.printStackTrace();
        }
        return null;
    }
}
