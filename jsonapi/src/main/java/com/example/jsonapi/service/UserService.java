package com.example.jsonapi.service;
import com.example.jsonapi.entity.User;
import com.example.jsonapi.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

@Service
public class UserService {

	
	
	private UserRepository userRepository;
	 private ObjectMapper objectMapper=new ObjectMapper();
	public UserService(UserRepository userRepository) {
		this.userRepository=userRepository;

	}
	
	@PostConstruct
	public void init() {
		try {
			 File file = new File("C:\\Users\\ASUS\\Documents\\SECUREMAIN\\SECURIN_SOLUTIONS\\practice\\jsonapi\\src\\main\\resources\\data.json");
	            InputStream inputStream = new FileInputStream(file);
	            List<User> users = objectMapper.readValue(inputStream, new TypeReference<List<User>>() {});
	            userRepository.saveAll(users);


		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public List<User> getAllUser(){
		return userRepository.findAll();
	}
}
