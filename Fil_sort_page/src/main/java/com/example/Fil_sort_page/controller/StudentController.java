package com.example.Fil_sort_page.controller;


import com.example.Fil_sort_page.Entity.Student;
import com.example.Fil_sort_page.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private StudentService studentservice;
	
	
	@GetMapping
	public Page<Student> getStudent(
			
			@RequestParam(defaultValue="0") int page,
			@RequestParam(defaultValue="5") int size ,
			@RequestParam(defaultValue="name") String sortby,
			@RequestParam(defaultValue="asc") String  dir,
			@RequestParam(required=false) String name,
			@RequestParam(required=false) Integer age
			
			
			){
		return studentservice.getStudents(page, size, sortby, dir, name, age);
	}
	@PostMapping
	public Student addStudent(@RequestBody Student student) {
		return studentservice.addStudent(student);
	}
	@PostMapping("/bulk")
	 public List<Student> saveAllStudents(@RequestBody List<Student> students) {
	        return studentservice.saveAllStudents(students);
	    }

}
