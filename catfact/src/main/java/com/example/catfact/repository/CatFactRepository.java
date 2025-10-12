package com.example.catfact.repository;

import com.example.catfact.entity.CatFact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatFactRepository extends JpaRepository<CatFact, Long> {
}
