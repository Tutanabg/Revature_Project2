package com.revature.services;

import com.revature.models.Shop;
import com.revature.repositories.ShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService{

    @Autowired
    ShopRepo sr;


    @Override
    public Shop addShop(Shop s) {
        return sr.save(s);
    }

    @Override
    public Shop getShop(int id) {
        return sr.findById(id).get();
    }

    @Override
    public List<Shop> getAllShops() {
        return (List<Shop>) sr.findAll();
    }

    @Override
    public Shop updateShop(Shop change) {
        return sr.save(change);
    }

    @Override
    public boolean deleteShop(int id) {
        try{
            sr.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }
}
