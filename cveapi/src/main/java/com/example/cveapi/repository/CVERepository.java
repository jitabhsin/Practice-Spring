package com.example.cveapi.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cveapi.entity.CVE;

@Repository
public interface CVERepository extends JpaRepository<CVE, String> {
    Optional<CVE> findByCveId(String cveId);
    List<CVE> findByLastModifiedDateAfter(LocalDateTime date);
}
