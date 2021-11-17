package com.revature.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "menu_item_ingredients")
public class MenuItemIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_ingredient_id")
    private int itemIngredientID;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private MenuItem menuItem;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    public MenuItemIngredient() {
    }

    public MenuItemIngredient(int itemIngredientID, MenuItem menuItem, Ingredient ingredient) {
        this.itemIngredientID = itemIngredientID;
        this.menuItem = menuItem;
        this.ingredient = ingredient;
    }

    public int getItemIngredientID() {
        return itemIngredientID;
    }

    public void setItemIngredientID(int itemIngredientID) {
        this.itemIngredientID = itemIngredientID;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItemIngredient)) return false;
        MenuItemIngredient that = (MenuItemIngredient) o;
        return itemIngredientID == that.itemIngredientID && Objects.equals(menuItem, that.menuItem) && Objects.equals(ingredient, that.ingredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemIngredientID, menuItem, ingredient);
    }

    @Override
    public String toString() {
        return "MenuItemIngredient{" +
                "itemIngredientID=" + itemIngredientID +
                ", menuItem=" + menuItem +
                ", ingredient=" + ingredient +
                '}';
    }
}
