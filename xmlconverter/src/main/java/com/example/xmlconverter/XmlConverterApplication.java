package com.example.xmlconverter;

import com.example.xmlconverter.converter.XMLToProtobufConverter;
import com.example.xmlconverter.model.EmployeeProto;
import com.google.protobuf.Message;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class XmlConverterApplication implements CommandLineRunner {

    private final XMLToProtobufConverter converter;

    public XmlConverterApplication(XMLToProtobufConverter converter) {
        this.converter = converter;
    }

    public static void main(String[] args) {
        SpringApplication.run(XmlConverterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--- Starting XML to Protobuf Conversion ---");

        Path outputDir = Paths.get("output");
        Files.createDirectories(outputDir);

        // --- Sample 1 ---
        System.out.println("\n--- Processin g sample1.xml ---");
        File sample1 = new ClassPathResource("sample1.xml").getFile();
        Message employee1 = converter.convert(sample1, EmployeeProto.Employee.getDefaultInstance());
        Path outputFile1 = outputDir.resolve("employee1_output.txt");
        Files.write(outputFile1, employee1.toString().getBytes());
        System.out.println("Result saved to: " + outputFile1.toAbsolutePath());

        // --- Sample 2 ---
        System.out.println("\n--- Processing sample2.xml ---");
        File sample2 = new ClassPathResource("sample2.xml").getFile();
        Message employee2 = converter.convert(sample2, EmployeeProto.Employee.getDefaultInstance());
        Path outputFile2 = outputDir.resolve("employee2_output.txt");
        Files.write(outputFile2, employee2.toString().getBytes());
        System.out.println("Result saved to: " + outputFile2.toAbsolutePath());
    }
}
