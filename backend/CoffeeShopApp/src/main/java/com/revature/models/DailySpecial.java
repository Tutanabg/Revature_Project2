package com.revature.models;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="daily_specials")
public class DailySpecial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_special_id")
    private int dailySpecialID;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Column(name = "day_of_week")
    private int dayOfWeek;

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;


    public DailySpecial() {
    }

    public DailySpecial(int dailySpecialID, Shop shop, int dayOfWeek, MenuItem menuItem) {
        this.dailySpecialID = dailySpecialID;
        this.shop = shop;
        this.dayOfWeek = dayOfWeek;
        this.menuItem = menuItem;
    }

    public int getDailySpecialID() {
        return dailySpecialID;
    }

    public void setDailySpecialID(int dailySpecialID) {
        this.dailySpecialID = dailySpecialID;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DailySpecial)) return false;
        DailySpecial that = (DailySpecial) o;
        return getDailySpecialID() == that.getDailySpecialID() && getDayOfWeek() == that.getDayOfWeek() && Objects.equals(getShop(), that.getShop()) && Objects.equals(getMenuItem(), that.getMenuItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDailySpecialID(), getShop(), getDayOfWeek(), getMenuItem());
    }

    @Override
    public String toString() {
        return "DailySpecial{" +
                "dailySpecialID=" + dailySpecialID +
                ", shop=" + shop +
                ", dayOfWeek=" + dayOfWeek +
                ", menuItem=" + menuItem +
                '}';
    }
}
