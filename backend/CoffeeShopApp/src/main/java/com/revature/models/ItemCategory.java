package com.revature.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item_categories")
public class ItemCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryID;

    @Column
    private String category;

    public ItemCategory() {
    }

    public ItemCategory(int categoryID, String category) {
        this.categoryID = categoryID;
        this.category = category;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemCategory)) return false;
        ItemCategory that = (ItemCategory) o;
        return getCategoryID() == that.getCategoryID() && Objects.equals(getCategory(), that.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategoryID(), getCategory());
    }

    @Override
    public String toString() {
        return "ItemCategory{" +
                "categoryID=" + categoryID +
                ", category='" + category + '\'' +
                '}';
    }
}
