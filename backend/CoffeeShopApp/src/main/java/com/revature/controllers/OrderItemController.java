package com.revature.controllers;

import com.revature.models.Order;
import com.revature.models.OrderItem;
import com.revature.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin
public class OrderItemController {

    @Autowired
    OrderItemService ois;


    @GetMapping("/orderItems")
    public List<OrderItem> getAllOrderItems(){
        return ois.getAllOrderItems();
    }


    @GetMapping("/orderItems/{id}")
    public OrderItem getOrderItem(@PathVariable("id") int id){
        return ois.getOrderItem(id);
    }


    @PostMapping(value = "/orderItems", consumes = "application/json", produces = "application/json")
    public OrderItem addOrderItem(@RequestBody OrderItem oi){
        return ois.addOrderItem(oi);
    }


    @PutMapping(value = "/orderItems/{id}", consumes = "application/json", produces = "application/json")
    public OrderItem updateOrderItem(@PathVariable("id") String id, @RequestBody OrderItem change) {
        change.setOrderItemID(Integer.parseInt(id));
        return ois.updateOrderItem(change);
    }


    @DeleteMapping(value = "/orderItems/{id}")
    public boolean deleteOrderItem(@PathVariable String id) {
        int deleteId = -1;
        try{
            deleteId = Integer.parseInt(id);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }
        return ois.deleteOrderItem(deleteId);
    }

    @GetMapping(value = "/orderItems/today/{day}")
    public List<OrderItem> getOrderItemsToday(@PathVariable String day){
        try {
            return ois.getOrderItemsForToday(day);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
