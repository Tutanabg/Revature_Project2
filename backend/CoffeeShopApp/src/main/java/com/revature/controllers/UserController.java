package com.revature.controllers;

import com.revature.models.User;
import com.revature.models.UserAddress;
import com.revature.services.UserAddressService;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService us;

    @Autowired
    UserAddressService uas;

    @GetMapping(value = "/users")
    public List<String> getAllUsernames() {
        List<User> userList = us.getAllUsers();
        List<String> usernameList = new ArrayList<>();
        for (User u: userList) {
            usernameList.add(u.getUsername());
        }
        return usernameList;
    }


    @GetMapping(value = "/login")
    public ResponseEntity<UserAddress> getUserByLogin(@RequestHeader("username") String username, @RequestHeader("pa$$word") String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        UserAddress userAddress = uas.getUserAddressByUser(user);
        if(userAddress != null){
            return new ResponseEntity<>(userAddress, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(value = "/users")
    public ResponseEntity<User> updateUser (@RequestBody User u) {
        if(us.updateUser(u) != null){
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "/users")
    public ResponseEntity<User> addUser (@RequestBody User u) {
        return new ResponseEntity<>(us.addUser(u), HttpStatus.CREATED);

    }

    @PostMapping(value = "/userAddresses")
    public ResponseEntity<UserAddress> addUserAddress (@RequestBody UserAddress userAddress) {
        return new ResponseEntity<>(uas.addUserAddress(userAddress), HttpStatus.CREATED);
    }

}
