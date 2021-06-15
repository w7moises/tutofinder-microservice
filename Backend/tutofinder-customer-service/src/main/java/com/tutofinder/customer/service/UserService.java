package com.tutofinder.customer.service;

import com.tutofinder.customer.entities.User;

import java.util.List;

public interface UserService{
    List<User> getUsers();
    User findByUsername(String username);
    User registerUser(User user,Long id);
}
