package com.example.cveapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cveapi.entity.CVE;
import com.example.cveapi.repository.CVERepository;

@Service
public class CVEService {

    @Autowired
    private CVERepository cveRepository;

    public Optional<CVE> getCVEById(String cveId) {
        return cveRepository.findByCveId(cveId);
    }

    public List<CVE> getCVEsModifiedAfter(LocalDateTime date) {
        return cveRepository.findByLastModifiedDateAfter(date);
    }

    public CVE saveCVE(CVE cve) {
        return cveRepository.save(cve);
    }

    public List<CVE> saveAll(List<CVE> cves) {
        return cveRepository.saveAll(cves);
    }
}
