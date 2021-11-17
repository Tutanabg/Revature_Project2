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
public class DailySpecialServiceTests {

    @Autowired
    DailySpecialService dss;

    @MockBean
    DailySpecialRepo dsr;

    ItemCategory ic1 = new ItemCategory(2,"Food");
    Picture p1 = new Picture(1,"dummyImageLink",null);
    MenuItem mi1 = new MenuItem(1,"espresso",2.50,10, ic1, p1);
    Shop s1 = new Shop(1, "shop", p1);
    DailySpecial ds1 = new DailySpecial(1, s1, 0, mi1);

    @Test
    void getAllDailySpecials() {

        List<DailySpecial> expected = new ArrayList<>();

        expected.add(ds1);

        Mockito.when(dsr.findAll()).thenReturn(expected);

        List<DailySpecial> actual = dss.getAllDailySpecials();

        Assertions.assertEquals(actual, expected);
    }

}
