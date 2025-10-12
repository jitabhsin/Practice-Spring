package com.weather.weatherapi.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.weather.weatherapi.model.Weather;
import com.weather.weatherapi.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CsvProcessorService {

    @Autowired
    private WeatherRepository weatherRepository;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm");
    private static final int BATCH_SIZE = 1000;

    @Transactional
    public void processCsv(String filePath) {
        List<Weather> weatherRecords = new ArrayList<>();
        int rowCount = 0;
        int skippedCount = 0;

        // Fetch all existing datetime_utc values to avoid repeated select queries
        Set<LocalDateTime> existingDatetimes = new HashSet<>();
        weatherRepository.findAll().forEach(w -> existingDatetimes.add(w.getDatetimeUtc()));

        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            csvReader.readNext(); // skip header
            String[] row;

            while ((row = csvReader.readNext()) != null) {
                LocalDateTime datetimeUtc;
                try {
                    datetimeUtc = LocalDateTime.parse(row[0], FORMATTER);
                } catch (Exception e) {
                    System.out.println("Skipping invalid datetime at row " + (rowCount + 2));
                    continue;
                }

                // Skip if datetime already exists
                if (existingDatetimes.contains(datetimeUtc)) {
                    skippedCount++;
                    continue;
                }

                Weather weather = new Weather();
                weather.setDatetimeUtc(datetimeUtc);
                weather.setCondition(row[1]);
                weather.setDewPoint(parseInteger(row[2]));
                weather.setFog(parseInteger(row[3]));
                weather.setHail(parseInteger(row[4]));
                weather.setHeatIndex(parseInteger(row[5]));
                weather.setHumidity(parseInteger(row[6]));
                weather.setPrecipitation(parseInteger(row[7]));
                weather.setPressure(parseInteger(row[8]));
                weather.setRain(parseInteger(row[9]));
                weather.setSnow(parseInteger(row[10]));
                weather.setTemperature(parseInteger(row[11]));
                weather.setThunder(parseInteger(row[12]));
                weather.setTornado(parseInteger(row[13]));
                weather.setVisibility(parseDouble(row[14]));
                weather.setWindDirectionDegrees(parseInteger(row[15]));
                weather.setWindDirection(row[16]);
                weather.setWindGust(parseDouble(row[17]));
                weather.setWindChill(parseInteger(row[18]));
                weather.setWindSpeed(parseDouble(row[19]));

                weatherRecords.add(weather);
                existingDatetimes.add(datetimeUtc); // add to set to avoid duplicates
                rowCount++;

                // Save in batches
                if (weatherRecords.size() >= BATCH_SIZE) {
                    weatherRepository.saveAll(weatherRecords);
                    weatherRepository.flush(); // ensure batch is persisted
                    weatherRecords.clear();
                    System.out.println("Saved batch of " + BATCH_SIZE + " records. Total processed: " + rowCount + ", Skipped: " + skippedCount);
                }
            }

            // Save remaining records
            if (!weatherRecords.isEmpty()) {
                weatherRepository.saveAll(weatherRecords);
                weatherRepository.flush();
                System.out.println("Saved final batch of " + weatherRecords.size() + " records.");
            }

            System.out.println("Successfully loaded " + rowCount + " records from CSV into database. Skipped " + skippedCount + " duplicates.");

        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException("Error processing CSV file", e);
        }
    }

    private Integer parseInteger(String value) {
        try {
            if (value == null || value.trim().isEmpty() || value.equals("-9999")) return null;
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Double parseDouble(String value) {
        try {
            if (value == null || value.trim().isEmpty()) return null;
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}