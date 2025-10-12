package com.example.cveapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

// DTO to map NVD API response
@JsonIgnoreProperties(ignoreUnknown = true)
public class NVDResponseDTO {

    private List<CVEItemWrapper> vulnerabilities;

    public List<CVEItemWrapper> getVulnerabilities() {
        return vulnerabilities;
    }

    public void setVulnerabilities(List<CVEItemWrapper> vulnerabilities) {
        this.vulnerabilities = vulnerabilities;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CVEItemWrapper {
        @JsonProperty("cve")
        private CVEItem cve;

        public CVEItem getCve() {
            return cve;
        }

        public void setCve(CVEItem cve) {
            this.cve = cve;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CVEItem {
        private String id;
        private String published;
        private String lastModified;
        private List<Description> descriptions;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPublished() {
            return published;
        }

        public void setPublished(String published) {
            this.published = published;
        }

        public String getLastModified() {
            return lastModified;
        }

        public void setLastModified(String lastModified) {
            this.lastModified = lastModified;
        }

        public List<Description> getDescriptions() {
            return descriptions;
        }

        public void setDescriptions(List<Description> descriptions) {
            this.descriptions = descriptions;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Description {
        private String lang;
        private String value;

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}