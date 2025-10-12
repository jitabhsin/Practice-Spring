package com.example.cveapi.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.cveapi.entity.CVE;
import com.example.cveapi.service.CVEService;

@RestController
@RequestMapping("/api/cves")
public class CVEController {

    @Autowired
    private CVEService cveService;

    // Get CVE by ID
    @GetMapping("/{cveId}")
    public ResponseEntity<CVE> getCVEById(@PathVariable String cveId) {
        return ResponseEntity.of(cveService.getCVEById(cveId));
    }

    // Get CVEs modified in last 'days'
    @GetMapping("/modified")
    public ResponseEntity<List<CVE>> getCVEsModifiedInLastDays(@RequestParam int days) {
        LocalDateTime fromDate = LocalDateTime.now().minusDays(days);
        List<CVE> cves = cveService.getCVEsModifiedAfter(fromDate);
        return ResponseEntity.ok(cves);
    }
}
