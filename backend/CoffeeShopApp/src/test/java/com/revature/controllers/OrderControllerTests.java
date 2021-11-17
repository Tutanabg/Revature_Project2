package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.revature.models.*;
import com.revature.services.OrderService;
import cucumber.api.java.gl.E;
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

@AutoConfigureMockMvc
@SpringBootTest(classes = com.revature.app.CoffeeShopAppApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class OrderControllerTests {
    @MockBean
    OrderService os;
    @Autowired
    MockMvc mvc;
    Gson gson = new Gson();

    UserRole ur1 = new UserRole(1,"customer");
    User u1 = new User(1,"first","last","123456789","email","user","password",ur1);
    OrderStatus os1 = new OrderStatus(1,"Order Received");
    PaymentType pt1 = new PaymentType(1, "cash");
    Order o = new Order(1, 1636495200000L,os1,u1,pt1,true);

    @Test
    void getOrderById() throws Exception {
        Mockito.when(os.getOrder(1)).thenReturn(new Order(1, 1636495200000L,os1,u1,pt1,true));
        ResultActions ra = mvc.perform(MockMvcRequestBuilders.get("/orders/1"));
        ra.andExpect(MockMvcResultMatchers.status().isOk());
        ra = mvc.perform(MockMvcRequestBuilders.get("/orders/fa"));
        ra.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    void addOrder() throws Exception {
        Mockito.when(os.addOrder(o)).thenReturn(o);
        ResultActions ra = mvc.perform(MockMvcRequestBuilders.post("/orders").content(new ObjectMapper().writeValueAsString(o)).contentType(MediaType.APPLICATION_JSON_VALUE));

        ra.andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    void getAllOrders() throws Exception {
        ResultActions ra = mvc.perform(MockMvcRequestBuilders.get("/orders"));
        ra.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void updateOrder() throws Exception {
        o.getOrderStatus().setStatus("Ready");
        o.getOrderStatus().setstatusID(2);
        Mockito.when(os.updateOrder(o)).thenReturn(o);
        ResultActions ra = mvc.perform(MockMvcRequestBuilders
                .put("/orders/1", 1)
                .content(new ObjectMapper().writeValueAsString(o))
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        ra.andExpect(MockMvcResultMatchers.status().isOk());

        ra = mvc.perform(MockMvcRequestBuilders
                .put("/orders/fa", 1)
                .content(new ObjectMapper().writeValueAsString(o))
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        ra.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    void deleteOrder() throws Exception {
        ResultActions ra = mvc.perform(MockMvcRequestBuilders.delete("/orders/1",1));
        ra.andExpect(MockMvcResultMatchers.status().isOk());
        ra = mvc.perform(MockMvcRequestBuilders.delete("/orders/fa",1));
        ra.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    void getOrderByDay() throws Exception {
        ResultActions ra = mvc.perform(MockMvcRequestBuilders.get("/orders/day/{day}",1636444800));
        ra.andExpect(MockMvcResultMatchers.status().isOk());
        ra = mvc.perform(MockMvcRequestBuilders.get("/orders/day/{day}","af"));
        ra.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    void advanceOrder() throws Exception {
        Mockito.when(os.advanceOrder(o)).thenReturn(o);
        ResultActions ra = mvc.perform(MockMvcRequestBuilders
                .put("/orders/advance/{id}", 1)
                .content(new ObjectMapper().writeValueAsString(o))
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        ra.andExpect(MockMvcResultMatchers.status().isOk());
        ra = mvc.perform(MockMvcRequestBuilders
                .put("/orders/advance/{id}", "fa")
                .content(new ObjectMapper().writeValueAsString(o))
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        ra.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
