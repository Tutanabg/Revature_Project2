package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.revature.models.*;
import com.revature.services.OrderService;
import com.revature.services.PictureService;
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
public class PictureControllerTest {

    @MockBean
    PictureService ps;

    @Autowired
    MockMvc mvc;

    @Autowired
    Gson gson;

    Picture p1 = new Picture(1,"dummyImageLink1", null);
    Picture p2 = new Picture(1,"dummyImageLink2", null);

    @Test
    void getPictureById() throws Exception {

        Mockito.when(ps.getPicture(1)).thenReturn(new Picture(1, "Coffee",null));

        ResultActions ra = mvc.perform(MockMvcRequestBuilders.get("/picture/1"));

        ra.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void addPicture() throws Exception {

        Mockito.when(ps.addPicture(p1)).thenReturn(p1);
        ResultActions ra = mvc.perform(MockMvcRequestBuilders.post("/picture").content(new ObjectMapper().writeValueAsString(p1)).contentType(MediaType.APPLICATION_JSON_VALUE));
        ra.andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void getAllPicturess() throws Exception {

        ResultActions ra = mvc.perform(MockMvcRequestBuilders.get("/pictures"));
        ra.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updatePicture() throws Exception {

        Mockito.when(ps.updatePicture(p1)).thenReturn(p2);

        mvc.perform(MockMvcRequestBuilders.put("/picture/1")
                .content(gson.toJson(p2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    
    @Test
    void deletePicture() throws Exception {
        
        ResultActions ra = mvc.perform(MockMvcRequestBuilders.delete("/picture/1",1));
        ra.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
