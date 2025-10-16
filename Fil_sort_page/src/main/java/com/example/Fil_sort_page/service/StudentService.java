package com.example.Fil_sort_page.service;

import org.springframework.stereotype.Service;

import com.example.Fil_sort_page.Entity.*;
import com.example.Fil_sort_page.repository.*;
//import com.sun.tools.javac.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import java.util.*;
@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repository;
	
	public Page<Student> getStudents(int page , int size , String sortby , String dir , String name , Integer age){
		Sort sort =dir.equalsIgnoreCase("asc")?Sort.by(sortby).ascending():Sort.by(sortby).descending();
				
	   PageRequest pageable = PageRequest.of(page, size);
	  
   if(name!=null && age!=null) {
	   return repository.findByNameContainingIgnoreCaseAndAge(name, page, pageable);
	   
   }else if(name!=null) {
	   return repository.findByNameContainingIgnoreCase(name, pageable);
   }else if(age!=null) {
	   return repository.findByAge(age, pageable);
   }else {
	   return repository.findAll(pageable);
   }
	}
	
	public Student addStudent(Student student) {
		return repository.save(student);
	}
	 public List<Student> saveAllStudents(List<Student> students) {
	        return repository.saveAll(students);
	    }
	
}
