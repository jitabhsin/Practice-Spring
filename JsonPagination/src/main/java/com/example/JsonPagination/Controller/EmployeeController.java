package com.example.JsonPagination.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.JsonPagination.Entity.Employee;
import com.example.JsonPagination.service.EmployeeService;



@RestController
@RequestMapping("/employees")
public class EmployeeController {
  @Autowired
  private EmployeeService service;
  
  
  @GetMapping("/all")
  public Page<Employee>getAll(@RequestParam int page , @RequestParam int size , @RequestParam String sortBy ){
	  return service.getAll(page, size, sortBy);
  }
  
  @GetMapping("/filter/department")
  public Page<Employee> filterByDepartment(
          @RequestParam String department,
          @RequestParam int page,
          @RequestParam int size,
          @RequestParam String sortBy){
      return service.findByDepartment(size, page, department, sortBy);
  }

  @GetMapping("/filter/name")
  public Page<Employee> filterByName(
          @RequestParam String name,
          @RequestParam int page,
          @RequestParam int size,
          @RequestParam String sortBy){
      return service.filterByName(name, page, size, sortBy);
  }

  @GetMapping("/filter/email")
  public Page<Employee> filterByEmail(
          @RequestParam String emailDomain,
          @RequestParam int page,
          @RequestParam int size,
          @RequestParam String sortBy){
      return service.filterByEmailDomain(emailDomain, page, size, sortBy);
  }

  @GetMapping("/filter/name-department")
  public Page<Employee> filterByNameAndDepartment(
          @RequestParam String name,
          @RequestParam String department,
          @RequestParam int page,
          @RequestParam int size,
          @RequestParam String sortBy){
      return service.filterByNameAndDepartment(name, department, page, size, sortBy);
  }
}
