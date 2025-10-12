# Practice-Spring

This repository is a collection of various hands-on projects developed to practice and demonstrate skills in **Java**, **Spring Boot**, and related technologies. Each folder contains a standalone project focusing on a specific use case, from API development and database integration to data conversion utilities.

# Projects Overview

Below is a summary of each project included in this repository.

# 1. XML to Protobuf Converter (xmlconverter)

## Project Type

Maven Java (Spring-style console app) with Protocol Buffers.

## Description

A command-line utility that converts XML data into Google's Protocol Buffers format based on a predefined .proto schema.

## Key Features

* Uses protoc to generate Java classes from employee.proto.
* Reads sample XML files (sample1.xml, sample2.xml).
* Outputs serialized protobuf data to text files.

## How to Build/Run

```
# Build the project
mvn package

# Run the generated JAR from the target directory
java -jar target/xml-converter-0.0.1-SNAPSHOT.jar
```

# 2. Weather API (weatherapi)

## Project Type

Maven Java.

## Description

A foundational project intended to work with weather data. Currently in an early stage of development.

## Key Features

* Includes testset.csv for data processing.
* Contains basic project structure and dependencies.

## How to Build

```
mvn package
```

# 3. JSON ↔ XML Converter (JSON_XML_VICE)

## Project Type

Maven Java utility.

## Description

A simple service for converting data between JSON and XML formats.

## Key Features

* Provides JsonXmlConverterService for two-way conversion.
* Includes sample data.json and data.xml for testing.

## How to Build

```
mvn package
```

# 4. JSON API (jsonapi)

## Project Type

Maven Spring Boot.

## Description

A RESTful API demonstrating standard CRUD operations for a User entity using JSON.

## Key Features

* Follows a layered architecture: Controller, Service, Repository.
* Uses Spring Data JPA for database interactions.
* Includes unit tests.

## How to Build/Run

```
mvn spring-boot:run
```

# 5. Excel to MySQL Importer (excelmysql)

## Project Type

Maven Spring Boot.

## Description

A web application for importing data from Excel into a MySQL database and vice-versa.

## Key Features

* Manages Person entities.
* ExcelService handles Excel file processing.
* Integrates with MySQL (configured in application.properties).

## How to Build/Run

```
# Ensure MySQL is running and configured
mvn spring-boot:run
```

# 6. CVE API (cveapi)

## Project Type

Maven Spring Boot.

## Description

An API integrating with the National Vulnerability Database (NVD) to fetch and manage CVE data.

## Key Features

* NVDService for REST calls to NVD API.
* CVEController exposes endpoints.
* Uses DTOs (NVDResponseDTO) for mapping API responses.

## How to Build/Run

```
mvn spring-boot:run
```

# 7. Employee API (employeeapi)

## Project Type

Maven Spring Boot.

## Description

A CRUD REST API for managing employee records.

## Key Features

* Standard Controller-Service-Repository pattern.
* Clean and scalable project structure.

## How to Build/Run

```
mvn spring-boot:run
```

# 8. Cat Fact Service (catfact)

## Project Type

Maven Spring Boot.

## Description

A fun microservice that consumes a third-party API to fetch random cat facts.

## Key Features

* CatFactController exposes the endpoint.
* CatFactService handles external API calls.
* Uses CatFactDto to structure the data.

## How to Build/Run

```
mvn spring-boot:run
```

# 9. JSON to XML CLI Converter (assessment1)

## Project Type

Maven CLI utility.

## Description

A simple CLI tool for converting a JSON file to XML, built as a sample assessment.

## Key Features

* Cli.java serves as the main entry point.
* Uses ConverterFactory to create the converter instance.
* Includes sample input/output files (example_input.json, example_output.xml).

## How to Build

```
mvn package
```
