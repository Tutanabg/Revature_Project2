package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.revature.models.*;
import com.revature.services.OrderService;
import com.revature.services.ShopService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = com.revature.app.CoffeeShopAppApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class ShopControllerTest {

    @MockBean
    ShopService ss;

    @Autowired
    MockMvc mvc;

    @Autowired
    Gson gson;

    Picture p = new Picture(1,"dummyPictureLink", null);
    Shop s1 = new Shop(1, "Moscow", p);
    Shop s2 = new Shop(1, "Pullman", p);

    @Test
    void getShopById() throws Exception {

        Mockito.when(ss.getShop(1)).thenReturn(s1);

        mvc.perform(MockMvcRequestBuilders.get("/shop/1")
                        .content(gson.toJson(s1))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    void addShop() throws Exception {
        Mockito.when(ss.addShop(s1)).thenReturn(s1);
        ResultActions ra = mvc.perform(MockMvcRequestBuilders.post("/shop").content(new ObjectMapper().writeValueAsString(s1)).contentType(MediaType.APPLICATION_JSON_VALUE));
        ra.andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void getAllshops() throws Exception {
        ResultActions ra = mvc.perform(MockMvcRequestBuilders.get("/shops"));
        ra.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateShop() throws Exception {

        Mockito.when(ss.updateShop(s1)).thenReturn(s2);

        mvc.perform(MockMvcRequestBuilders.put("/shop/1")
                .content(gson.toJson(s2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteShop() throws Exception {

        mvc.perform(MockMvcRequestBuilders.delete("/shop/1")
                        .content(gson.toJson(s1))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }
}
