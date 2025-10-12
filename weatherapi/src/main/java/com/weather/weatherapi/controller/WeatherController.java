package com.weather.weatherapi.controller;

import com.weather.weatherapi.dto.TemperatureStatsDTO;
import com.weather.weatherapi.dto.WeatherDTO;
import com.weather.weatherapi.service.WeatherService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
@Validated
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/month")
    public ResponseEntity<List<WeatherDTO>> getWeatherByMonth(
            @RequestParam @Min(1996) @Max(2025) int year,
            @RequestParam @Min(1) @Max(12) int month) {
        return ResponseEntity.ok(weatherService.getWeatherByMonth(year, month));
    }

    @GetMapping("/date")
    public ResponseEntity<List<WeatherDTO>> getWeatherByDate(
            @RequestParam @Min(1996) @Max(2025) int year,
            @RequestParam @Min(1) @Max(12) int month,
            @RequestParam @Min(1) @Max(31) int day) {
        return ResponseEntity.ok(weatherService.getWeatherByDate(year, month, day));
    }

    @GetMapping("/temperature-stats")
    public ResponseEntity<TemperatureStatsDTO> getTemperatureStats(
            @RequestParam @Min(1996) @Max(2025) int year,
            @RequestParam @Min(1) @Max(12) int month) {
        return ResponseEntity.ok(weatherService.getTemperatureStats(year, month));
    }
}