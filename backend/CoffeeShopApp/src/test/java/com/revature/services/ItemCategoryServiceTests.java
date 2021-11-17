package com.revature.services;

import com.revature.models.*;
import com.revature.repositories.*;
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
public class ItemCategoryServiceTests {

    @Autowired
    ItemCategoryService ics;

    @MockBean
    ItemCategoryRepo icr;

    ItemCategory ic1 = new ItemCategory(2,"Food");

    @Test
    void getAllItemCategories() {

        List<ItemCategory> expected = new ArrayList<>();

        expected.add(ic1);

        Mockito.when(icr.findAll()).thenReturn(expected);

        List<ItemCategory> actual = ics.getAllItemCategories();

        Assertions.assertEquals(actual, expected);
    }

}
