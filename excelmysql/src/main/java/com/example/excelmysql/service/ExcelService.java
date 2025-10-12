package com.example.excelmysql.service;

import com.example.excelmysql.entity.Person;
import com.example.excelmysql.repository.PersonRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;

import java.io.FileInputStream;
import java.io.File;
@Service
public class ExcelService {

    private final PersonRepository repository;

    public ExcelService(PersonRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        try (InputStream is = new FileInputStream(
                new File("C:\\Users\\ASUS\\Documents\\SECUREMAIN\\SECURIN_SOLUTIONS\\practice\\excelmysql\\src\\main\\resources\\Book1.xlsx")
        )) {
            savePersonFromExcel(is); // no need to check null, FileInputStream throws exception if file not found
            System.out.println("Excel file loaded successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void savePersonFromExcel(InputStream is) {
        try (XSSFWorkbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            List<Person> persons = new ArrayList<>();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;
                Person person = new Person();
                person.setName(row.getCell(0).getStringCellValue());
                person.setEmail(row.getCell(1).getStringCellValue());
                person.setAge((int) row.getCell(2).getNumericCellValue());
                persons.add(person);
            }

            repository.saveAll(persons); // save to DB
            System.out.println("Excel data saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Person> getAllPersons() {
        return repository.findAll(); // fetch all
    }
}


