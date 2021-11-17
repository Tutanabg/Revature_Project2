package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.User;
import com.revature.models.UserAddress;
import com.revature.models.UserRole;
import com.revature.services.UserAddressService;
import com.revature.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = com.revature.app.CoffeeShopAppApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class UserControllerTests {
    @MockBean
    UserService us;

    @MockBean
    UserAddressService uas;

    @Autowired
    MockMvc mvc;

    @Autowired
    Gson gson;

    static UserRole ur1 = new UserRole(1,"customer");
    static User u1 = new User(1,"first","last","123456789","email","user1","password1",ur1);
    static User u2 = new User(2,"second","last","123456789","email","user2","password2",ur1);
    static User u3 = new User(3,"third","last","123456789","email","user3","password3",ur1);
    static UserAddress ua1 = new UserAddress(1,2125,"street","city",u1);
    static List<User> userList = new ArrayList<>();

    @Test
    void getAllUsernames() throws Exception {
        userList.add(u1);
        userList.add(u2);
        userList.add(u3);
        Mockito.when(us.getAllUsers()).thenReturn(userList);
        ResultActions ra = mvc.perform(MockMvcRequestBuilders.get("/users"));
        ra.andExpect(status().isOk());
    }

    @Test
    void getUserByLoginFC() throws Exception {
        Mockito.when(uas.getUserAddressByUser(u1)).thenReturn(null);
        ResultActions ra = mvc.perform(MockMvcRequestBuilders.get("/login").header("username",ua1.getUser().getUsername()).header("pa$$word",ua1.getUser().getPassword()));
        ra.andExpect(status().isNoContent());
    }

    @Test
    void addUser() throws Exception {
        Mockito.when(us.addUser(u1)).thenReturn(u1);
        ResultActions ra = mvc.perform(MockMvcRequestBuilders.post("/users").content(gson.toJson(u1)).contentType(MediaType.APPLICATION_JSON));
        ra.andExpect(status().isCreated());
    }

    @Test
    void addUserAddress() throws Exception {
        Mockito.when(uas.addUserAddress(ua1)).thenReturn(ua1);
        ResultActions ra = mvc.perform(MockMvcRequestBuilders.post("/userAddresses").content(gson.toJson(ua1)).contentType(MediaType.APPLICATION_JSON));
        ra.andExpect(status().isCreated());
    }

    @Test
    void updateUserFC() throws Exception {
        Mockito.when(us.updateUser(u1)).thenReturn(null);
        ResultActions ra = mvc.perform(MockMvcRequestBuilders.put("/users").content(gson.toJson(ua1)).contentType(MediaType.APPLICATION_JSON));
        ra.andExpect(status().isNoContent());
    }
}
