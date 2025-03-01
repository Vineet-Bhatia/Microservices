package com.Icwd.user.service.services;

import java.util.List;

import com.Icwd.user.service.entity.User;



public interface UserServices {
	
	User createUser(User user);

    List<User> getAllUsers();  // Changed from getALLUsers() to getAllUsers()

    User getUser(String id);

    User updateUserDetail(String id, User user);

    void deleteUser(String id);
}
