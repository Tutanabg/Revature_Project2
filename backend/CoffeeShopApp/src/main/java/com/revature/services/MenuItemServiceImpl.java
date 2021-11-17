package com.revature.services;

import com.revature.models.MenuItem;
import com.revature.repositories.MenuItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService{

    @Autowired
    MenuItemRepo mir;

    @Override
    public List<MenuItem> getAllMenuItems() {
        return (List<MenuItem>) mir.findAll();
    }
}
