package com.example.xlsheet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.example.xlsheet.*;
import com.example.xlsheet.student.*;
import com.example.xlsheet.service.StudentService;
import org.springframework.beans.factory.annotation.*;
@RestController
@RequestMapping("/student")
public class StudentController {
	
   @Autowired
   private  StudentService service;
   
	@GetMapping("/list")
	public List<Student>getAllStudents(){
		return service.getAllStudents();
	}
}
