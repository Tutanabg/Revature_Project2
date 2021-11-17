package com.revature.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ingredient_order_items")
public class IngredientOrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_order_item_id")
    private int ingredientOrderItemId;

    @ManyToOne
    @JoinColumn(name = "order_item_id")
    private OrderItem orderItem;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(name = "ingredient_count")
    private int ingredientCount;

    public IngredientOrderItem() {
    }

    public IngredientOrderItem(int ingredientOrderItemId, OrderItem orderItem, Ingredient ingredient, int ingredientCount) {
        this.ingredientOrderItemId = ingredientOrderItemId;
        this.orderItem = orderItem;
        this.ingredient = ingredient;
        this.ingredientCount = ingredientCount;
    }

    public int getIngredientOrderItemId() {
        return ingredientOrderItemId;
    }

    public void setIngredientOrderItemId(int ingredientOrderItemId) {
        this.ingredientOrderItemId = ingredientOrderItemId;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getIngredientCount() {
        return ingredientCount;
    }

    public void setIngredientCount(int ingredientCount) {
        this.ingredientCount = ingredientCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IngredientOrderItem)) return false;
        IngredientOrderItem that = (IngredientOrderItem) o;
        return getIngredientOrderItemId() == that.getIngredientOrderItemId() && getIngredientCount() == that.getIngredientCount() && Objects.equals(getOrderItem(), that.getOrderItem()) && Objects.equals(getIngredient(), that.getIngredient());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIngredientOrderItemId(), getOrderItem(), getIngredient(), getIngredientCount());
    }

    @Override
    public String toString() {
        return "IngredientOrderItem{" +
                "ingredientOrderItemId=" + ingredientOrderItemId +
                ", orderItem=" + orderItem +
                ", ingredient=" + ingredient +
                ", ingredientCount=" + ingredientCount +
                '}';
    }
}
