package com.example.xlsheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository(Student , String) {

}
