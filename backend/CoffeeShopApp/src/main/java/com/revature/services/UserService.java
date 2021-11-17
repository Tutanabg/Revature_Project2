package com.revature.services;

import com.revature.models.User;

import java.util.List;

public interface UserService {
    public User addUser(User u);
    public User getUser(int id);
    public List<User> getAllUsers();
    public User updateUser(User change);
    public Boolean deleteUser(int id);

    public User getUserByLogin(User u);
}
