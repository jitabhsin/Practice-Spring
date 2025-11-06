package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;


@RestController
@RequestMapping("/Student")
public class StudentController {

	
	@Autowired
	private  StudentService service;
	
	@PostMapping
	public Student addStudent(@RequestBody Student student) {
		
		return service.addStudent(student);
		
	
		
	}
	
	@GetMapping("/{id}")
	public Student getStudent(@PathVariable Long id) {
		
		return service.getStudent(id);
	}
	
	
	@PutMapping("/{id}")
	public  Student updateStudent(@PathVariable Long id ,@RequestBody Student student) {
		return service.updateStudent(id , student);
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteStudent(@PathVariable Long id) {
	   service.deleteStudent(id);
	   
	   
	}
	@GetMapping("/all")
	public List<Student> getAll(){
	   return service.getAll();
	}
	
}
