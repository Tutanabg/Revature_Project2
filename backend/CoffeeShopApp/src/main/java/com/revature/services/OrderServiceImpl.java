package com.revature.services;

import com.revature.models.Order;
import com.revature.models.OrderStatus;
import com.revature.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepo or;

    @Override
    public Order addOrder(Order o) {
        return or.save(o);
    }

    @Override
    public List<Order> getAllOrders() {
        return (List<Order>) or.findAll();
    }

    @Override
    public Order getOrder(int id) {
        return or.findById(id).get();
    }

    @Override
    public Order updateOrder(Order change) {
        return or.save(change);
    }

    @Override
    public boolean deleteOrder(int id) {
        try{
            or.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Order> getOrdersByDay(long dayStart) {
        // will return list of orders for a certain day, 1 day = 86400000ms
        // get the day and get the orders for orders>day and orders<day

        Long dayEnd = dayStart + 86400000L;
        return or.getOrdersByDay(dayStart,dayEnd);
    }

    @Override
    public Order advanceOrder(Order o) {
        OrderStatus orderStatus = o.getOrderStatus();
        Order updatedOrder;
        if(orderStatus.getStatus().equals("Order Received")){
            //advance the order
            o.getOrderStatus().setStatus("Ready");
            o.getOrderStatus().setstatusID(2);
            return updatedOrder = or.save(o);
        } else if (orderStatus.getStatus().equals("Ready")&&o.getDelivery()) {
            //if it is ready and is for delivery will change to delivery in progress when delivery man picks up
            o.getOrderStatus().setStatus("Delivery in Progress");
            o.getOrderStatus().setstatusID(3);
            return updatedOrder = or.save(o);
        } else if (orderStatus.getStatus().equals("Ready")||orderStatus.getStatus().equals("Delivered")){
            o.getOrderStatus().setStatus("Order Complete");
            o.getOrderStatus().setstatusID(5);
            System.out.println("in here" + o.getOrderStatus());
            return updatedOrder = or.save(o);
        } else if (orderStatus.getStatus().equals("Delivery in Progress")) {
            o.getOrderStatus().setStatus("Delivered");
            o.getOrderStatus().setstatusID(5);
            return updatedOrder = or.save(o);
        } else {
            return o;
        }
    }


}
