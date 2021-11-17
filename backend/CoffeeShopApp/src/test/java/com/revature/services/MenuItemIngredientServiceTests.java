package com.revature.services;

import com.revature.models.*;
import com.revature.repositories.MenuItemIngredientRepo;
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
public class MenuItemIngredientServiceTests {

    @Autowired
    MenuItemIngredientService miis;

    @MockBean
    MenuItemIngredientRepo miir;

    Picture p1 = new Picture(1,"dummyImageLink",null);
    ItemCategory ic1 = new ItemCategory(2,"Food");
    MenuItem mi1 = new MenuItem(1,"espresso",2.50,10,ic1,p1);
    Ingredient i1 = new Ingredient(1, "almond milk", 0.50);
    MenuItemIngredient mii1 = new MenuItemIngredient(1, mi1, i1);


    @Test
    void getAllMenuItemIngredients() {
        
        List<MenuItemIngredient> expected = new ArrayList<>();

        expected.add(mii1);

        Mockito.when(miir.findAll()).thenReturn(expected);

        List<MenuItemIngredient> actual = miis.getAllMenuItemIngredients();

        Assertions.assertEquals(actual, expected);
    }


}
