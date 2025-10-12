package com.example.excelmysql.controller;
import com.example.excelmysql.entity.Person;
import com.example.excelmysql.service.ExcelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {
	
	 private final ExcelService excelService;
	 public PersonController(ExcelService excelService) {
	        this.excelService = excelService;
	    }

	 @GetMapping("/persons")
	    public List<Person> getAllPersons() {
	        return excelService.getAllPersons();
	    }

}
