package com.weather.weatherapi.dto;

public class TemperatureStatsDTO {
    private Integer maxTemperature;
    private Integer minTemperature;
    private Double medianTemperature;

    public TemperatureStatsDTO(Integer maxTemperature, Integer minTemperature, Double medianTemperature) {
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.medianTemperature = medianTemperature;
    }

    // Getters and Setters
    public Integer getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Integer maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public Integer getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Integer minTemperature) {
        this.minTemperature = minTemperature;
    }

    public Double getMedianTemperature() {
        return medianTemperature;
    }

    public void setMedianTemperature(Double medianTemperature) {
        this.medianTemperature = medianTemperature;
    }
}