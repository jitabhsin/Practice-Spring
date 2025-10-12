package com.example.employeeapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.employeeapi.entity.Employee;
import com.example.employeeapi.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
 private final EmployeeRepository repository;
 
 public EmployeeService(EmployeeRepository repository) {
	 this.repository=repository;
 }
 
 public List<Employee> getAllEmployees(){
	 return repository.findAll();
 }
 
 public Employee saveEmployee(Employee employee) {
	 return repository.save(employee);
 }
 public Employee getEmployeeById(Long id) {
	 return repository.findById(id).orElse(null);
 }
 public Employee updateEmployee(Long id , Employee newData) {
	 Employee existingEmployee = repository.findById(id).orElse(null);
	 if(existingEmployee!=null) {
		 existingEmployee.setName(newData.getName());
		 existingEmployee.setDepartment(newData.getDepartment());
		 existingEmployee.setSalary(newData.getSalary());
		 
		 return repository.save(existingEmployee);
		 
		 
	 }else
	 {
		 return null;
	 }
 }
	 public void deleteEmployee(Long id) {
	  repository.deleteById(id);	 
	 }
 }
