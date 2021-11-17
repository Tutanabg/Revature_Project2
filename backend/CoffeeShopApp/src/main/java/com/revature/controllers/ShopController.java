package com.revature.controllers;


import com.revature.models.Shop;
import com.revature.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ShopController {

    @Autowired
    ShopService ss;

    @GetMapping("/shops")
    public List<Shop> getAllShops() {
        return ss.getAllShops();
    }

    @GetMapping("/shop/{id}")
    public Shop getShopByID(@PathVariable("id") String id) {
        return ss.getShop((Integer.parseInt(id)));
    }


    @PostMapping(value = "/shop", consumes = "application/json", produces = "application/json")
    public Shop addShop(@RequestBody Shop s) {
        return ss.addShop(s);
    }

    @PutMapping(value = "shop/{id}", consumes = "application/json", produces = "application/json")
    public Shop updateShop(@PathVariable("id") String id, @RequestBody Shop change) {
        change.setShopID(Integer.parseInt(id));
        return ss.updateShop(change);
    }

   // @Authorized
    @DeleteMapping("shop/{id}")
    public boolean deleteShop(@PathVariable("id") int id) {

        return ss.deleteShop(id);
    }


}
