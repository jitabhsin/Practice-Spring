package com.example.JsonPagination.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.JsonPagination.Entity.Employee;
import com.example.JsonPagination.Repository.EmployeeRepository;
@Service
public class EmployeeService {

	
	
	
	@Autowired
	private EmployeeRepository repository;
	
	private Pageable getPageable(int page , int size , String sortBy) {
		return PageRequest.of(page , size , Sort.by(sortBy));
	}
	
	public Page<Employee> getAll(int page , int size , String sortBy){
		return repository.findAll(getPageable(page , size , sortBy));
	}
	
    public Page<Employee> findByDepartment(int page , int size ,String department , String sortBy){
    	return repository.findByDepartment(department, getPageable(page , size , sortBy));
    	
    	
    }
    public Page<Employee> filterByName(String name, int page, int size, String sortBy){
        return repository.findByName(name, getPageable(page, size, sortBy));
    }

    public Page<Employee> filterByEmailDomain(String emailDomain, int page, int size, String sortBy){
        return repository.findByEmail(emailDomain, getPageable(page, size, sortBy));
    }

    public Page<Employee> filterByNameAndDepartment(String name, String department, int page, int size, String sortBy){
        return repository.findByNameContainingAndDepartment(name, department, getPageable(page, size, sortBy));
    }
    
}
