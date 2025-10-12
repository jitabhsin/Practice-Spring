package com.example.jsonapi.controller;

import com.example.jsonapi.entity.User;
import com.example.jsonapi.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<User> getAllUser(){
		return service.getAllUser();	}
	 	
	
}
