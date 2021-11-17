package com.revature.services;

import com.revature.models.User;
import com.revature.models.UserAddress;
import com.revature.models.UserRole;
import com.revature.repositories.UserAddressRepo;
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
public class UserAddressServiceTests {

    @Autowired
    UserAddressService uas;

    @MockBean
    UserAddressRepo uar;

    @MockBean
    UserRepo ur;

    static UserRole ur1 = new UserRole(1,"customer");
    static User u1 = new User(1,"first","last","123456789","email","user","password",ur1);
    static UserAddress ua1 = new UserAddress(1,2125,"street","city",u1);

    @Test
    void addUserAddress(){
        UserAddress newUserAddress = new UserAddress(2125,"street","city",u1);
        Mockito.when(uar.save(newUserAddress)).thenReturn(ua1);
        newUserAddress = uas.addUserAddress(newUserAddress);
        Assertions.assertEquals(ua1,newUserAddress);
    }

    @Test
    void getUserAddress(){
        Optional<UserAddress> userAddressOptional = Optional.of(ua1);
        Mockito.when(uar.findById(ua1.getAddressID())).thenReturn(userAddressOptional);
        UserAddress result = uas.getUserAddress(ua1.getAddressID());
        Assertions.assertEquals(result, ua1);
    }

    @Test
    void getAllUserAddresses(){
        List<UserAddress> userList = new ArrayList<>();
        userList.add(ua1);
        Mockito.when(uar.findAll()).thenReturn(userList);
        List<UserAddress> result = uas.getAllUserAddresses();
        Assertions.assertEquals(result, userList);
    }

    @Test
    void updateUserAddress(){
        UserAddress newUserAddress = new UserAddress(2125,"street","city",u1);
        Mockito.when(uar.save(newUserAddress)).thenReturn(ua1);
        newUserAddress = uas.updateUserAddress(newUserAddress);
        Assertions.assertEquals(2125, newUserAddress.getZipCode());
        Assertions.assertEquals("street", newUserAddress.getStreet());
        Assertions.assertEquals("city", newUserAddress.getCity());
        Assertions.assertEquals(u1, newUserAddress.getUser());
        Assertions.assertEquals(ua1,newUserAddress);
    }

    @Test
    void deleteUserAddressSC(){
        Mockito.doNothing().when(uar).deleteById(ua1.getAddressID());
        boolean result = uas.deleteUserAddress(ua1.getAddressID());
        Assertions.assertTrue(result);
    }

    @Test
    void deleteUserAddressFC(){
        Mockito.doThrow(IllegalArgumentException.class).when(uar).deleteById(ua1.getAddressID());
        boolean result = uas.deleteUserAddress(ua1.getAddressID());
        Assertions.assertFalse(result);
    }

    @Test
    void getUserAddressByUserSC(){
        Mockito.when(ur.findByUsernameAndPassword(u1.getUsername(), u1.getPassword())).thenReturn(u1);
        Mockito.when(uar.findByUser(u1)).thenReturn(ua1);
        UserAddress result = uas.getUserAddressByUser(u1);
        Assertions.assertEquals(result,ua1);
    }

    @Test
    void getUserAddressByUserFC(){
        Mockito.when(ur.findByUsernameAndPassword(u1.getUsername(), u1.getPassword())).thenReturn(null);
        UserAddress result = uas.getUserAddressByUser(u1);
        Assertions.assertNull(result);
    }
}
