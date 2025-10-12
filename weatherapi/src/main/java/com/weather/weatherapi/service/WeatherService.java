package com.weather.weatherapi.service;

import com.weather.weatherapi.dto.TemperatureStatsDTO;
import com.weather.weatherapi.dto.WeatherDTO;
import com.weather.weatherapi.model.Weather;
import com.weather.weatherapi.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;  

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public List<WeatherDTO> getWeatherByMonth(int year, int month) {
        List<Weather> weatherList = weatherRepository.findByYearAndMonth(year, month);
        return weatherList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<WeatherDTO> getWeatherByDate(int year, int month, int day) {
        LocalDateTime start = LocalDateTime.of(year, month, day, 0, 0);
        LocalDateTime end = LocalDateTime.of(year, month, day, 23, 59);
        List<Weather> weatherList = weatherRepository.findByDatetimeUtcBetween(start, end);
        return weatherList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public TemperatureStatsDTO getTemperatureStats(int year, int month) {
        List<Object[]> stats = weatherRepository.findTemperatureStatsByYearAndMonth(year, month);
        if (stats.isEmpty()) {
            return new TemperatureStatsDTO(null, null, null);
        }
        Object[] stat = stats.get(0);
        Integer maxTemp = (Integer) stat[0];
        Integer minTemp = (Integer) stat[1];
        Double avgTemp = (Double) stat[2];
        return new TemperatureStatsDTO(maxTemp, minTemp, avgTemp);
    }

    private WeatherDTO convertToDTO(Weather weather) {
        WeatherDTO dto = new WeatherDTO();
        dto.setDatetimeUtc(weather.getDatetimeUtc().format(FORMATTER));
        dto.setCondition(weather.getCondition());
        dto.setTemperature(weather.getTemperature());
        dto.setHumidity(weather.getHumidity());
        dto.setPressure(weather.getPressure());
        return dto;
    }
}