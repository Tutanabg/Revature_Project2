package com.revature.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private int shopID;

    @Column(name = "shop_location")
    private String shopLocation;

    @ManyToOne
    @JoinColumn(name = "shop_pic")
    private Picture shopPic;

    public Shop() {
    }

    public Shop(int shopID, String shopLocation, Picture shopPic) {
        this.shopID = shopID;
        this.shopLocation = shopLocation;
        this.shopPic = shopPic;
    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public String getShopLocation() {
        return shopLocation;
    }

    public void setShopLocation(String shopLocation) {
        this.shopLocation = shopLocation;
    }

    public Picture getShopPic() {
        return shopPic;
    }

    public void setShopPic(Picture shopPic) {
        this.shopPic = shopPic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shop)) return false;
        Shop shop = (Shop) o;
        return getShopID() == shop.getShopID() && Objects.equals(getShopLocation(), shop.getShopLocation()) && Objects.equals(getShopPic(), shop.getShopPic());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShopID(), getShopLocation(), getShopPic());
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopID=" + shopID +
                ", shopLocation='" + shopLocation + '\'' +
                ", shopPic=" + shopPic +
                '}';
    }
}
