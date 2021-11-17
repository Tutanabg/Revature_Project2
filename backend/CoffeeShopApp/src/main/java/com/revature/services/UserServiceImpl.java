package com.revature.services;

import com.revature.models.User;
import com.revature.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo ur;

    @Override
    public User addUser(User user) {
        return ur.save(user);
    }

    @Override
    public User getUser(int id) {
        Optional<User> userOptional = ur.findById(id);
        return userOptional.orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) ur.findAll();
    }

    @Override
    public User updateUser(User change) {
        User u = ur.findByFirstNameAndLastName(change.getFirstName(),change.getLastName());
        if(u != null){
            if((u.getUsername().equals(change.getUsername()))&&(u.getEmail().equals(change.getEmail()))&&(u.getPhoneNumber().equals(change.getPhoneNumber()))){
                u.setPassword(change.getPassword());
                return ur.save(u);
            }
        }
        return null;
    }

    @Override
    public Boolean deleteUser(int id) {
        try {
            ur.deleteById(id);
            return true;
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUserByLogin(User user) {
        return ur.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

}
