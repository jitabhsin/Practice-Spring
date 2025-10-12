package com.example.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.util.Random;

public class JsonXmlConverterService {

    private final ObjectMapper jsonMapper = new ObjectMapper();
    private final XmlMapper xmlMapper = new XmlMapper();
    private final Random n = new Random();
    private static int counter = new Random().nextInt(1000); // initialize counter randomly

    public void convertJsonToXml(String inputFilePath) {
        try {
            JsonNode jsonNode = jsonMapper.readTree(new File(inputFilePath));
            String output = generateFileName("xml");
            File outputFile = new File(output);
            xmlMapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, jsonNode);
            System.out.println("✅ JSON converted to XML: " + outputFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void convertXmlToJson(String inputFilePath) {
        try {
            JsonNode jsonNode = xmlMapper.readTree(new File(inputFilePath));
            String output = generateFileName("json");
            File outputFile = new File(output);
            jsonMapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, jsonNode);
            System.out.println("✅ XML converted to JSON: " + outputFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateFileName(String extension) {
        counter++;
        return "output_" + counter + "." + extension;
    }

    public static void main(String[] args) {
        JsonXmlConverterService service = new JsonXmlConverterService();
        service.convertJsonToXml("src/main/resources/data.json");
        service.convertXmlToJson("src/main/resources/data.xml");
    }
}
