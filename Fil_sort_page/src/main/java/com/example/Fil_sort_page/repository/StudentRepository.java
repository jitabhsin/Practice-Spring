package com.example.Fil_sort_page.repository;

import com.example.Fil_sort_page.Entity.*;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.*;

@Repository
public interface StudentRepository extends JpaRepository<Student , Long>{
 Page<Student> findByNameContainingIgnoreCase(String name , Pageable pageable);
 Page<Student> findByAge(int age , Pageable pageable);
 //Page<Student> findByNameContainingIgnoreCaseAndAge(String name , int age , Pageable pageable);
 Page<Student> findByNameContainingIgnoreCaseAndAge(String name, Integer age, Pageable pageable);
	
}
