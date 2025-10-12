package com.weather.weatherapi.repository;

import com.weather.weatherapi.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

    List<Weather> findByDatetimeUtcBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT w FROM Weather w WHERE YEAR(w.datetimeUtc) = :year AND MONTH(w.datetimeUtc) = :month")
    List<Weather> findByYearAndMonth(int year, int month);

    @Query("SELECT MAX(w.temperature), MIN(w.temperature), AVG(w.temperature) FROM Weather w WHERE YEAR(w.datetimeUtc) = :year AND MONTH(w.datetimeUtc) = :month")
    List<Object[]> findTemperatureStatsByYearAndMonth(int year, int month);

    boolean existsByDatetimeUtc(LocalDateTime datetimeUtc);
}