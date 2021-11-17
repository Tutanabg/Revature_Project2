package com.revature.services;

import com.revature.models.*;
import com.revature.repositories.DailySpecialRepo;
import com.revature.repositories.MenuItemRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = com.revature.app.CoffeeShopAppApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class MenuItemServiceTests {
    
    @Autowired
    MenuItemService mis;

    @MockBean
    MenuItemRepo mir;

    Picture p1 = new Picture(1,"dummyImageLink",null);
    ItemCategory ic1 = new ItemCategory(2,"Food");
    MenuItem mi1 = new MenuItem(1,"espresso",2.50,10,ic1,p1);


    @Test
    void getAllMenuItems() {

        List<MenuItem> expected = new ArrayList<>();

        expected.add(mi1);

        Mockito.when(mir.findAll()).thenReturn(expected);

        List<MenuItem> actual = mis.getAllMenuItems();

        Assertions.assertEquals(actual, expected);
    }


}
