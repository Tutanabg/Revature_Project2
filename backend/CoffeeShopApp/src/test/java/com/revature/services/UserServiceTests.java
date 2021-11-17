package com.revature.services;

import com.revature.models.User;
import com.revature.models.UserAddress;
import com.revature.models.UserRole;
import com.revature.repositories.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = com.revature.app.CoffeeShopAppApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class UserServiceTests {
    @Autowired
    UserService us;

    @MockBean
    UserRepo ur;

    static UserRole ur1 = new UserRole(1,"customer");
    static User u1 = new User(1,"first","last","123456789","email","user","password",ur1);
    static UserAddress ua1 = new UserAddress(1,2125,"street","city",u1);

    @Test
    void addUser(){
        User newUser = new User("first","last","123456789","email","user","password",ur1);
        Mockito.when(ur.save(newUser)).thenReturn(u1);
        newUser = us.addUser(newUser);
        Assertions.assertEquals(u1,newUser);
    }

    @Test
    void getUser(){
        Optional<User> userOptional = Optional.of(u1);
        Mockito.when(ur.findById(u1.getUserID())).thenReturn(userOptional);
        User result = us.getUser(u1.getUserID());
        Assertions.assertEquals(result, u1);
    }

    @Test
    void getAllUsers(){
        List<User> userList = new ArrayList<>();
        userList.add(u1);
        Mockito.when(ur.findAll()).thenReturn(userList);
        List<User> result = us.getAllUsers();
        Assertions.assertEquals(result, userList);
    }

    @Test
    void updateUserFC(){
        User newUser = u1;
        newUser.setPassword("Password12345");
        Mockito.when(ur.findByFirstNameAndLastName(newUser.getFirstName(),newUser.getFirstName())).thenReturn(u1);
        newUser = us.updateUser(newUser);
        Assertions.assertNull(newUser);
    }

    @Test
    void deleteUserSC(){
        Mockito.doNothing().when(ur).deleteById(u1.getUserID());
        boolean result = us.deleteUser(u1.getUserID());
        Assertions.assertTrue(result);
    }

    @Test
    void deleteUserFC(){
        Mockito.doThrow(IllegalArgumentException.class).when(ur).deleteById(u1.getUserID());
        boolean result = us.deleteUser(u1.getUserID());
        Assertions.assertFalse(result);
    }

    @Test
    void getUserByLogin(){
        Mockito.when(ur.findByUsernameAndPassword(u1.getUsername(), u1.getPassword())).thenReturn(u1);
        User result = us.getUserByLogin(u1);
        Assertions.assertEquals(result,u1);
    }
}
