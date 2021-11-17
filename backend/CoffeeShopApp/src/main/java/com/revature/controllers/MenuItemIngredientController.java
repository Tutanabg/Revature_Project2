package com.revature.controllers;

import com.revature.models.MenuItemIngredient;
import com.revature.services.MenuItemIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class MenuItemIngredientController {

    @Autowired
    MenuItemIngredientService miis;

    @GetMapping("/menuItemIngredients")
    public List<MenuItemIngredient> getAllMenuItemIngredients() {
        return miis.getAllMenuItemIngredients();
    }
}
