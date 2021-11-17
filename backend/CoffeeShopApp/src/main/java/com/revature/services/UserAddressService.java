package com.revature.services;

import com.revature.models.User;
import com.revature.models.UserAddress;

import java.util.List;

public interface UserAddressService {
    public UserAddress addUserAddress(UserAddress ua);
    public UserAddress getUserAddress(int id);
    public List<UserAddress> getAllUserAddresses();
    public UserAddress updateUserAddress(UserAddress change);
    public Boolean deleteUserAddress(int id);

    public UserAddress getUserAddressByUser(User u);
}
