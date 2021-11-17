package com.revature.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private int ingredientID;


    @Column(name = "ingredient_name")
    private String ingredientName;

    @Column(columnDefinition = "numeric (4,2)")
    private double cost;

    public Ingredient() {
    }

    public Ingredient(int ingredientID, String ingredientName, double cost) {
        this.ingredientID = ingredientID;
        this.ingredientName = ingredientName;
        this.cost = cost;
    }

    public int getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(int ingredientID) {
        this.ingredientID = ingredientID;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;
        Ingredient that = (Ingredient) o;
        return getIngredientID() == that.getIngredientID() && Double.compare(that.getCost(), getCost()) == 0 && Objects.equals(getIngredientName(), that.getIngredientName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIngredientID(), getIngredientName(), getCost());
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredientID=" + ingredientID +
                ", ingredientName='" + ingredientName + '\'' +
                ", cost=" + cost +
                '}';
    }
}
