package com.example.cveapi.service;

import com.example.cveapi.dto.NVDResponseDTO;
import com.example.cveapi.entity.CVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class NVDService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private CVEService cveService;

    // CORRECTED: Use the correct formatter for date strings without timezone offsets.
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    // Fetch all CVEs from NVD API
    public void fetchAndSaveAllCVEs() {
        int startIndex = 0;
        int resultsPerPage = 2000; // NVD max per page
        boolean hasMore = true;

        while (hasMore) {
            String url = "https://services.nvd.nist.gov/rest/json/cves/2.0"
                    + "?startIndex=" + startIndex
                    + "&resultsPerPage=" + resultsPerPage;

            NVDResponseDTO response = restTemplate.getForObject(url, NVDResponseDTO.class);

            if (response != null && response.getVulnerabilities() != null) {
                List<CVE> cves = new ArrayList<>();
                for (NVDResponseDTO.CVEItemWrapper wrapper : response.getVulnerabilities()) {
                    NVDResponseDTO.CVEItem item = wrapper.getCve();
                    String id = item.getId();

                    String desc = item.getDescriptions().stream()
                            .filter(d -> "en".equals(d.getLang()))
                            .map(NVDResponseDTO.Description::getValue)
                            .findFirst()
                            .orElse("No English description available.");

                    LocalDateTime published = null;
                    if (item.getPublished() != null && !item.getPublished().isEmpty()) {
                        try {
                            // This will now parse correctly
                            published = LocalDateTime.parse(item.getPublished(), formatter);
                        } catch (Exception e) {
                            System.out.println("⚠️ Invalid published date for CVE " + id + ": " + item.getPublished());
                        }
                    }

                    LocalDateTime modified = null;
                    if (item.getLastModified() != null && !item.getLastModified().isEmpty()) {
                        try {
                            // This will now parse correctly
                            modified = LocalDateTime.parse(item.getLastModified(), formatter);
                        } catch (Exception e) {
                            System.out.println("⚠️ Invalid modified date for CVE " + id + ": " + item.getLastModified());
                        }
                    }

                    CVE cve = new CVE(id, desc, published, modified);
                    cves.add(cve);
                }

                cveService.saveAll(cves);
                System.out.println("Saved " + cves.size() + " CVEs. Current index: " + startIndex);
                startIndex += resultsPerPage;

                hasMore = response.getVulnerabilities().size() == resultsPerPage;

                try {
                    Thread.sleep(6000); // Wait 6 seconds for NVD API rate limiting
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    hasMore = false;
                }

            } else {
                hasMore = false;
            }
        }
    }
}