package com.example.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;

@Service
public class StudentService {
              
	@Autowired
	private  StudentRepository repository;
	
	
	public Student addStudent(Student student) {
		return repository.save(student);
	}
	
	public Student getStudent(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public  Student updateStudent(Long id , Student student) {
		
		Student olddata = repository.findById(id).orElse(null);
		
		
		if(olddata!=null) {
			olddata.setName( student.getName());
			olddata.setDept(student.getDept());
			olddata.setT_marks(student.getT_marks());
			return repository.save(olddata);
		}else {
			return null;
		}
	}
	
	public List<Student> getAll(){
		return repository.findAll();
	}
	
	public void deleteStudent(Long id) {
		repository.deleteById(id);
	}
	
	
	
	
}
