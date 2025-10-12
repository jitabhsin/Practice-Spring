package com.example.cveapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.cveapi.service.NVDService;

@SpringBootApplication
public class CVEApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CVEApiApplication.class, args);
    }

    // Optional: fetch CVEs on startup
    @Bean
    CommandLineRunner run(NVDService nvdService) {
        return args -> {
            System.out.println("Fetching CVEs from NVD API...");
            nvdService.fetchAndSaveAllCVEs();
            System.out.println("CVE fetching completed.");
        };
    }
}
