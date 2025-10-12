package com.securin.converters;

import java.io.File;
import java.io.IOException;

public class Cli {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Sorry, you need to provide two file paths.");
            System.out.println("Usage: java -jar your-program.jar <input-json-file> <output-xml-file>");
            return;
        }

        String inputFilePath = args[0];
        String outputFilePath = args[1];

        File inputFile = new File(inputFilePath);
        File outputFile = new File(outputFilePath);

        if (!inputFile.exists()) {
            System.out.println("Error: The input file was not found: " + inputFilePath);
            return;
        }

        System.out.println("Starting conversion...");

        XMLJSONConverterI converter = ConverterFactory.createXMLJSONConverter();

        try {
            converter.convertJSONtoXML(inputFile, outputFile);
            System.out.println("Conversion finished successfully!");
            System.out.println("Output written to: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Conversion failed due to an IOException:");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred:");
            e.printStackTrace();
        }
    }
}
