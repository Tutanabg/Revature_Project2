package com.revature.controllers;


import com.revature.services.DailySpecialService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@AutoConfigureMockMvc
@SpringBootTest(classes = com.revature.app.CoffeeShopAppApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class MenuItemIngredientControllerTests {
    @MockBean
    DailySpecialService os;
    @Autowired
    MockMvc mvc;

    @Test
    void getAllMenuItemIngredients() throws Exception {
        ResultActions ra = mvc.perform(MockMvcRequestBuilders.get("/menuItemIngredients"));
        ra.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
