package com.revature.controllers;

import com.revature.models.IngredientOrderItem;
import com.revature.services.IngredientOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class IngredientOrderItemController {

    @Autowired
    IngredientOrderItemService iois;

    @GetMapping("/ingredientOrderItems")
    public List<IngredientOrderItem> getAllIngredientOrderItems(){
        return iois.getAllIngredientOrderItems();
    }
    @GetMapping("/ingredientOrderItems/{id}")
    public IngredientOrderItem getIngredientOrderItem(@PathVariable("id") int id){
        return iois.getIngredientOrderItem(id);
    }

    @PostMapping(value = "/ingredientOrderItems/submitOrder", consumes = {"application/json"}, produces = "application/json")
    public List<List<IngredientOrderItem>> submitOrder(@RequestBody List<List<IngredientOrderItem>> o){

        return iois.submitOrder(o);
    }

    @PostMapping(value = "/ingredientOrderItems", consumes = {"application/json"}, produces = "application/json")
    public IngredientOrderItem addIngredientOrderItem(@RequestBody IngredientOrderItem o){
        return iois.addIngredientOrderItem(o);
    }
    @PutMapping(value = "/ingredientOrderItems/{id}", consumes = {"application/json"}, produces = "application/json")
    public IngredientOrderItem updateIngredientOrderItem(@PathVariable("id") String id, @RequestBody IngredientOrderItem change) {
        return iois.updateIngredientOrderItem(change);
    }
}
