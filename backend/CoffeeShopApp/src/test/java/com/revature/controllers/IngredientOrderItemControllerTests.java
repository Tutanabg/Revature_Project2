package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.*;
import com.revature.services.IngredientOrderItemService;
import com.revature.services.OrderItemService;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = com.revature.app.CoffeeShopAppApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class IngredientOrderItemControllerTests {


    @MockBean
    IngredientOrderItemService iois;

    @Autowired
    MockMvc mvc;

    @Autowired
    Gson gson;



    Byte[] temp = null;
    UserRole ur1 = new UserRole(1,"customer");
    User u1 = new User(1,"first","last","123456789","email","user","password",ur1);
    UserAddress ua1 = new UserAddress(1,2125,"street","city",u1);
    Picture p1 = new Picture(1,"shop",temp);
    Shop s1 = new Shop(1, "shop", p1);
    ItemCategory ic1 = new ItemCategory(1,"food");
    MenuItem mi1 = new MenuItem(1,"a",5,10,ic1,p1);
    DailySpecial ds1 = new DailySpecial(1,s1,1,mi1);
    Ingredient i1 = new Ingredient(1, "Ingredient", 2.50);
    OrderStatus os1 = new OrderStatus(1,"Ready");
    PaymentType pt1 = new PaymentType(1, "cash");
    Order o1 = new Order(1,11232455,os1,u1,pt1,true);
    OrderItem oi1 = new OrderItem(0,o1, mi1, 2);
    IngredientOrderItem ioi1 = new IngredientOrderItem(0, oi1, i1, 2);


    @Test
    void addIngredientOrderItem() throws Exception {

        IngredientOrderItem ioi1 = new IngredientOrderItem(0, oi1, i1, 2);

        Mockito.when(iois.addIngredientOrderItem(ioi1)).thenReturn(ioi1);

        mvc.perform(MockMvcRequestBuilders.post("/ingredientOrderItems")
                .content(gson.toJson(oi1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    void getIngredientOrderItem() throws Exception {

        IngredientOrderItem ioi1 = new IngredientOrderItem(0,oi1, i1, 2);


        Mockito.when(iois.getIngredientOrderItem(1)).thenReturn(ioi1);

        ResultActions ra = mvc.perform(MockMvcRequestBuilders.get("/ingredientOrderItems/1"));

        ra.andExpect(status().isOk());


    }

    @Test
    void getAllIngredientOrderItems() throws Exception {

        ResultActions ra = mvc.perform(MockMvcRequestBuilders.get("/ingredientOrderItems"));

        ra.andExpect(status().isOk());

    }

    @Test
    void updateIngredientOrderItem() throws Exception {

        IngredientOrderItem ioi1 = new IngredientOrderItem(1,oi1, i1, 2);
        IngredientOrderItem ioi2 = new IngredientOrderItem(1,oi1, i1, 3);

        Mockito.when(iois.updateIngredientOrderItem(ioi1)).thenReturn(ioi2);

        ResultActions ra = mvc.perform(MockMvcRequestBuilders.put("/ingredientOrderItems/1")
                .content(gson.toJson(ioi2))
                .contentType(MediaType.APPLICATION_JSON));

        ra.andExpect(status().isOk());
    }

    @Test
    void submitOrder() throws Exception {

        IngredientOrderItem ioi1 = new IngredientOrderItem(1,oi1, i1, 1);
        IngredientOrderItem ioi2 = new IngredientOrderItem(2 ,oi1, i1, 2);

        List<IngredientOrderItem> expectedCartItem = new ArrayList<>();
        expectedCartItem.add(ioi1);
        expectedCartItem.add(ioi2);

        List<List<IngredientOrderItem>> expectedCart = new ArrayList<>();
        expectedCart.add(expectedCartItem);

        Order o1NoID = new Order(0, 11232455, os1, u1, pt1, true);
        OrderItem oi1NoOrder = new OrderItem(0, o1NoID, mi1, 2);
        IngredientOrderItem ioi1NoOrder = new IngredientOrderItem(0, oi1NoOrder, i1, 1);
        IngredientOrderItem ioi2NoOrder = new IngredientOrderItem(0, oi1NoOrder, i1, 2);

        List<IngredientOrderItem> cartItemBeforeSubmit = new ArrayList<>();
        cartItemBeforeSubmit.add(ioi1NoOrder);
        cartItemBeforeSubmit.add(ioi2NoOrder);

        List<List<IngredientOrderItem>> cartBeforeOrder = new ArrayList<>();
        cartBeforeOrder.add(cartItemBeforeSubmit);

        Mockito.when(iois.submitOrder(cartBeforeOrder)).thenReturn(expectedCart);

        mvc.perform(MockMvcRequestBuilders.post("/ingredientOrderItems/submitOrder")
                .content(gson.toJson(expectedCart))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



    @Test
    void submitOrder2() throws Exception {

        List<List<IngredientOrderItem>> listOfLists = null;

        Mockito.when(iois.submitOrder(listOfLists)).thenReturn(listOfLists);

        ResultActions ra = mvc.perform(MockMvcRequestBuilders.post("/ingredientOrderItems/submitOrder")

                .content(gson.toJson(listOfLists))
                .contentType(MediaType.APPLICATION_JSON));

        ra.andExpect(status().is(200));


    }





}






