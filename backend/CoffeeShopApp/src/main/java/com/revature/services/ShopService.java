package com.revature.services;

import com.revature.models.Shop;

import java.util.List;

public interface ShopService {

    public Shop addShop(Shop s);
    public Shop getShop(int id);
    public List<Shop> getAllShops();
    public Shop updateShop(Shop change);
    public boolean deleteShop(int id);


}
