package com.revature.controllers;

import com.revature.models.ItemCategory;
import com.revature.services.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ItemCategoryController {

    @Autowired
    ItemCategoryService ics;

    @GetMapping("/itemCategories")
    public List<ItemCategory> getAllItemCategories() {
        return ics.getAllItemCategories();
    }
}
