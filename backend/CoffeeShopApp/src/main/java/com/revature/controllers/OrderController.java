package com.revature.controllers;

import com.revature.models.Order;
import com.revature.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.util.List;

@RestController
@CrossOrigin
public class OrderController {

    @Autowired
    OrderService os;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<>(os.getAllOrders(),HttpStatus.OK);
    }
    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") String id){
        try {
            int realid = Integer.parseInt(id);
            return new ResponseEntity<>(os.getOrder(realid), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "/orders", consumes = {"application/json"}, produces = "application/json")
    public ResponseEntity<Order> addOrder(@RequestBody Order o){
        return new ResponseEntity<>(os.addOrder(o), HttpStatus.OK);
    }
    @PutMapping(value = "/orders/{id}", consumes = {"application/json"}, produces = "application/json")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") String id, @RequestBody Order change) {
        try {
            int realid = Integer.parseInt(id);
            return new ResponseEntity<>(os.updateOrder(change), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping(value = "/orders/{id}")
    public ResponseEntity<Boolean> deleteOrder(@PathVariable String id) {
        int deleteId = -1;
        try{
           deleteId = Integer.parseInt(id);
            return new ResponseEntity<>(os.deleteOrder(deleteId),HttpStatus.OK);
        }catch (NumberFormatException e){
            e.printStackTrace();
            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/orders/day/{day}")
    public ResponseEntity<List<Order>> getOrdersByDay(@PathVariable String day){
        List<Order> dayOrders = null;
        long realDay = 0L;
        try{
            realDay = Long.parseLong(day);
            return new ResponseEntity<>(os.getOrdersByDay(realDay), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }
    @PutMapping(value = "/orders/advance/{id}", consumes = {"application/json"}, produces = "application/json")
    public ResponseEntity<Order> advanceOrder(@PathVariable("id") String id, @RequestBody Order change) {
        try{
            change.setorderID(Integer.parseInt(id));
            return new ResponseEntity<>(os.advanceOrder(change), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


}
