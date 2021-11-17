package com.revature.services;

import com.revature.models.User;
import com.revature.models.UserAddress;
import com.revature.repositories.UserAddressRepo;
import com.revature.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAddressServiceImpl implements UserAddressService{
    @Autowired
    UserAddressRepo uar;
    @Autowired
    UserRepo ur;

    @Override
    public UserAddress addUserAddress(UserAddress ua) {
        return uar.save(ua);
    }

    @Override
    public UserAddress getUserAddress(int id) {
        Optional<UserAddress> userAddressOptional = uar.findById(id);
        return userAddressOptional.orElse(null);
    }

    @Override
    public List<UserAddress> getAllUserAddresses() {
        return (List<UserAddress>) uar.findAll();
    }

    @Override
    public UserAddress updateUserAddress(UserAddress change) {
        return uar.save(change);
    }

    @Override
    public Boolean deleteUserAddress(int id) {
        try {
            uar.deleteById(id);
            return true;
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserAddress getUserAddressByUser(User u) {
        User user = ur.findByUsernameAndPassword(u.getUsername(),u.getPassword());
        if(user != null){
            return uar.findByUser(user);
        }else {
            return null;
        }
    }
}
