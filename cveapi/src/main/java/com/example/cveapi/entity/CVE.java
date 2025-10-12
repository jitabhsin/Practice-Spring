package com.example.cveapi.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cve")
public class CVE {

    @Id
    @Column(name = "cve_id", length = 50)
    private String cveId;

    // CORRECTED: Changed to a TEXT type to allow for very long descriptions
    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDateTime publishedDate;
    private LocalDateTime lastModifiedDate;

    public CVE() {}

    public CVE(String cveId, String description, LocalDateTime publishedDate, LocalDateTime lastModifiedDate) {
        this.cveId = cveId;
        this.description = description;
        this.publishedDate = publishedDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    // Getters & Setters...
    public String getCveId() { return cveId; }
    public void setCveId(String cveId) { this.cveId = cveId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getPublishedDate() { return publishedDate; }
    public void setPublishedDate(LocalDateTime publishedDate) { this.publishedDate = publishedDate; }

    public LocalDateTime getLastModifiedDate() { return lastModifiedDate; }
    public void setLastModifiedDate(LocalDateTime lastModifiedDate) { this.lastModifiedDate = lastModifiedDate; }
}