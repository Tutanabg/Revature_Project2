package com.revature.services;

import com.revature.models.IngredientOrderItem;
import com.revature.models.Order;
import com.revature.models.OrderItem;
import com.revature.repositories.IngredientOrderItemRepo;
import com.revature.repositories.OrderItemRepo;
import com.revature.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientOrderItemServiceImpl implements IngredientOrderItemService {

    @Autowired
    IngredientOrderItemRepo ioir;

    @Autowired
    OrderItemRepo oir;

    @Autowired
    OrderRepo or;

    @Override
    public IngredientOrderItem addIngredientOrderItem(IngredientOrderItem actor) {
        return ioir.save(actor);
    }

    @Override
    public IngredientOrderItem getIngredientOrderItem(int id) {
        return ioir.findById(id).get();
    }

    @Override
    public List<IngredientOrderItem> getAllIngredientOrderItems() {
        return (List<IngredientOrderItem>) ioir.findAll();
    }

    @Override
    public IngredientOrderItem updateIngredientOrderItem(IngredientOrderItem newData) {
        return ioir.save(newData);
    }

    @Override
    public List<List<IngredientOrderItem>> submitOrder(List<List<IngredientOrderItem>> cart) {

        //put the order in the DB
        System.out.println(cart.get(0).get(0).getOrderItem());
        Order newOrder = or.save(cart.get(0).get(0).getOrderItem().getOrderID());

        for (List<IngredientOrderItem> cartItem : cart) {

            for (IngredientOrderItem ingredientPack : cartItem) {

                //give all the iois that order
                ingredientPack.getOrderItem().setOrderID(newOrder);
            }

            //save each orderItem (which now have orders attached)
            OrderItem newOrderItem = oir.save(cartItem.get(0).getOrderItem());

            for (IngredientOrderItem ingredientPack : cartItem) {

                //give each ioi the correct orderItem
                ingredientPack.setOrderItem(newOrderItem);

                if (ingredientPack.getIngredient() != null) {

                    //if the ioi actually has ingredients, put it in DB
                    //setting ID so that the service test works
                    ingredientPack.setIngredientOrderItemId(ioir.save(ingredientPack).getIngredientOrderItemId());
                }
            }
        }
        return cart;
    }

}
