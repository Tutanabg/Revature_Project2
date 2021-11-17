package com.revature.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private int itemID;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_price", columnDefinition = "numeric (4,2)")
    private double itemPrice;

    @Column(name = "item_prep_time")
    private long itemPrepTime;

    @ManyToOne
    @JoinColumn(name = "item_category")
    private ItemCategory itemCategory;

    @ManyToOne
    @JoinColumn(name = "item_pic")
    private Picture itemPic;

    public MenuItem() {
    }

    public MenuItem(int itemID, String itemName, double itemPrice, long itemPrepTime, ItemCategory itemCategory, Picture itemPic) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemPrepTime = itemPrepTime;
        this.itemCategory = itemCategory;
        this.itemPic = itemPic;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public long getItemPrepTime() {
        return itemPrepTime;
    }

    public void setItemPrepTime(long itemPrepTime) {
        this.itemPrepTime = itemPrepTime;
    }

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public Picture getItemPic() {
        return itemPic;
    }

    public void setItemPic(Picture itemPic) {
        this.itemPic = itemPic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem menuItem = (MenuItem) o;
        return getItemID() == menuItem.getItemID() && Double.compare(menuItem.getItemPrice(), getItemPrice()) == 0 && getItemPrepTime() == menuItem.getItemPrepTime() && Objects.equals(getItemName(), menuItem.getItemName()) && Objects.equals(getItemCategory(), menuItem.getItemCategory()) && Objects.equals(getItemPic(), menuItem.getItemPic());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemID(), getItemName(), getItemPrice(), getItemPrepTime(), getItemCategory(), getItemPic());
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "itemID=" + itemID +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", itemPrepTIme=" + itemPrepTime +
                ", itemCategory=" + itemCategory +
                ", itemPic=" + itemPic +
                '}';
    }
}
