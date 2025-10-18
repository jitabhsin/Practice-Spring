package com.example.xlsheet.service;
import com.example.xlsheet.repository.*;

import jakarta.annotation.PostConstruct;
import java.util.*;
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import com.example.xlsheet.student.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
@Service
public class StudentService {
	
	
	
	
	
   @Autowired
   private  StudentRepository repository;
   
   @PostConstruct
   public void init() {
	   try (InputStream f = new FileInputStream(new File("C:\\Users\\ASUS\\Documents\\practice\\xlsheet\\src\\main\\resources\\Book1.xlsx"))) {
	       try (XSSFWorkbook workbook = new XSSFWorkbook(f)) {
	           Sheet sheet=workbook.getSheetAt(0);
	           List<Student>stds=new ArrayList<>();
	           for(Row row :sheet) {
	        	   if(row.getRowNum()==0) continue;
	        	   Student std =new Student();
	        	   std.setName(row.getCell(0).getStringCellValue());
	        	   std.setSubject(row.getCell(1).getStringCellValue());
	        	   std.setMarks((int)row.getCell(2).getNumericCellValue());
	        	   
	        	stds.add(std);  
	           }
	           repository.saveAll(stds);
	           
	       }
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	}
   public List<Student> getAllStudents(){
	   return repository.findAll();
   }

   
}
