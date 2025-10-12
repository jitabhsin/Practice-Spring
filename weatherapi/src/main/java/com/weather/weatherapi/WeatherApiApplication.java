package com.weather.weatherapi;

import com.weather.weatherapi.service.CsvProcessorService;
import com.weather.weatherapi.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class WeatherApiApplication implements CommandLineRunner {

    @Autowired
    private CsvProcessorService csvProcessorService;

    @Autowired
    private WeatherRepository weatherRepository;

    public static void main(String[] args) {
        SpringApplication.run(WeatherApiApplication.class, args);
    }

    @Override
    public void run(String... args) {
        long existingCount = weatherRepository.count();
        String csvFilePath = "src/main/resources/testset.csv";

        if (existingCount == 0) {
            System.out.println("No existing data found in MySQL. Automatically loading data from CSV...");
            csvProcessorService.processCsv(csvFilePath);
        } else {
            System.out.println("Existing data found in MySQL (" + existingCount + " records).");
            System.out.print("Do you want to reload/append data from CSV? This may take time and memory for large files (y/n): ");
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine().trim().toLowerCase();
          //  scanner.close();

            if (userInput.equals("y") || userInput.equals("yes")) {
                System.out.println("Reloading/appending data from CSV...");
                csvProcessorService.processCsv(csvFilePath);
            } else {
                System.out.println("Skipping CSV loading to save time and memory.");
            }
        }

        System.out.println("🌤️ Weather API is ready! visit Read_ME file for instruction related to URL");
    }
}