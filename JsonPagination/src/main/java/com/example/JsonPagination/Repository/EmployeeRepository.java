package com.example.JsonPagination.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JsonPagination.Entity.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee , Long>{
	
	Page<Employee>findByDepartment(String department , Pageable pageable);
	
	Page<Employee>findByName(String name , Pageable pageable);
	
	Page<Employee>findByEmail(String email , Pageable pageable);
	
	Page<Employee>findByNameContainingAndDepartment(String name , String department , Pageable pageable);
	

}
