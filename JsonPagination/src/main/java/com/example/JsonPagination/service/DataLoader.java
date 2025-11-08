package com.example.JsonPagination.service;
import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.JsonPagination.Entity.Employee;
import com.example.JsonPagination.Repository.EmployeeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {
    
	
	@Autowired
	private EmployeeRepository repo;
	
	
	@PostConstruct
	public void init() {
		try{
			if(repo.count()==0) {
				ObjectMapper map = new ObjectMapper();
				File file = new File("src/main/resources/data.json");
				List<Employee> emp = map.readValue(file , new TypeReference<List<Employee>>() {});
				repo.saveAll(emp);
				System.out.println("Loaded");
				
						
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
