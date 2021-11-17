package com.revature.controllers;

import com.revature.models.MenuItem;
import com.revature.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class MenuItemController {

    @Autowired
    MenuItemService mis;

    @GetMapping("/menuItems")
    public List<MenuItem> getAllMenuItems() {
        return mis.getAllMenuItems();
    }
}
