package com.revature.services;

import com.revature.models.IngredientOrderItem;

import java.util.List;

public interface IngredientOrderItemService {

    public IngredientOrderItem addIngredientOrderItem(IngredientOrderItem actor);
    public IngredientOrderItem getIngredientOrderItem(int id);
    public List<IngredientOrderItem> getAllIngredientOrderItems();
    public IngredientOrderItem updateIngredientOrderItem(IngredientOrderItem newData);

    List<List<IngredientOrderItem>> submitOrder(List<List<IngredientOrderItem>> o);
}
