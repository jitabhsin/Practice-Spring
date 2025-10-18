package com.example.xlsheet.repository;
import com.example.xlsheet.student.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student , Long> {

}
