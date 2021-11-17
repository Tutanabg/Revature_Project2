package com.revature.services;

import com.revature.models.ItemCategory;
import com.revature.repositories.ItemCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService{

    @Autowired
    ItemCategoryRepo icr;

    @Override
    public List<ItemCategory> getAllItemCategories() {
        return (List<ItemCategory>) icr.findAll();
    }
}
