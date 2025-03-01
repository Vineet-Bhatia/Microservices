package com.Icwd.user.service.entity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Icwd.user.service.entity.User;
import com.Icwd.user.service.services.UserServices;

@RestController
@RequestMapping("/users")
public class UserController {
	
	
	@Autowired
	private UserServices userService;
	
	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody User user){
		User user1=userService.createUser(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	@GetMapping("/{userId}")  
	public ResponseEntity<User> getOneUser(@PathVariable String userId) {
	    User user = userService.getUser(userId);
	    return ResponseEntity.ok(user);
	}

	
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		List<User> allUser=userService.getAllUsers();
		return ResponseEntity.ok(allUser);
	}

}
