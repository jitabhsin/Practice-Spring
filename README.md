# Practice-Spring — Workspace Overview

This repository is a collection of small, independent Java/Maven projects focused on practicing and demonstrating skills in **Java**, **Spring Boot**, data conversion utilities, API development, and database integration. Each project resides in its own directory with a dedicated `pom.xml` for standalone building and running.

This README provides project-wise details, including descriptions, key features, build/run instructions, and entry points. Consult module-specific `HELP.md` or `READ_ME.md` files where available. For portability, build and run from each project's root directory.

## 1. assessment1 — JSON-to-XML CLI Converter

### Project Type
Maven CLI utility (Java 8).

### Description
A simple command-line tool for converting JSON files to XML format, built as a sample assessment.

### Key Features
- Uses `ConverterFactory` to instantiate converters.
- Main entry point: `com.securin.converters.Cli`.
- Includes sample files: `example_input.json` and `example_output.xml`.
- Maven Assembly plugin creates a `jar-with-dependencies` for easy distribution.

### How to Build/Run
From PowerShell (or terminal):
```powershell
cd assessment1
mvn clean package
java -jar target/json-to-xml-converter-1.0.0-jar-with-dependencies.jar example_input.json output.xml
```
- Args: Input JSON path, output XML path.

### Important Files
- `pom.xml`: Declares JSON dependencies and assembly plugin.
- `READ_ME.md`: Module-specific build/run instructions.
- Sources: `src/main/java/com/securin/converters/` (CLI and converter logic).
- Resources: `src/main/resources/` (samples).

## 2. xmlconverter — XML to Protobuf Converter

### Project Type
Maven Java (Spring-style console app) with Protocol Buffers (Java 17).

### Description
A command-line utility that converts XML data into Google's Protocol Buffers format using a predefined `.proto` schema.

### Key Features
- Compiles `employee.proto` to Java classes via `protobuf-maven-plugin`.
- Reads sample XML files (`sample1.xml`, `sample2.xml`).
- Outputs serialized protobuf data to text files.

### How to Build/Run
```powershell
cd xmlconverter
mvn package
java -jar target/xml-converter-0.0.1-SNAPSHOT.jar
```
- For Spring Boot mode: `mvn spring-boot:run`.

### Important Files
- `pom.xml`: Spring Boot + protobuf plugin configuration.
- Sources: `src/main/java/` (conversion logic).
- Resources: `src/main/resources/` (proto and XML samples).

## 3. JSON_XML_VICE — JSON ↔ XML Converter Utilities

### Project Type
Maven Java utility (Java 11+).

### Description
A bidirectional service for converting data between JSON and XML formats using Jackson.

### Key Features
- `JsonXmlConverterService` handles JSON-to-XML and XML-to-JSON conversions.
- Includes sample files: `data.json` and `data.xml` for testing.

### How to Build/Run
```powershell
cd JSON_XML_VICE
mvn package
```
- Utility only; integrate into other apps or run via custom main class.

### Important Files
- `pom.xml`: Jackson JSON and XML modules.
- Sources: `src/main/java/` (service class).
- Resources: `src/main/resources/` (samples).

## 4. jsonapi — JSON API Demo

### Project Type
Maven Spring Boot (Java 17).

### Description
A RESTful API demonstrating CRUD operations for a User entity using JSON, following a layered architecture.

### Key Features
- Layers: Controller, Service, Repository.
- Spring Data JPA for database interactions.
- Includes unit tests.
- Loads `data.json` (note: uses absolute paths — see known issues).

### How to Build/Run
```powershell
cd jsonapi
mvn spring-boot:run
```
- Access endpoints like `/users` for CRUD.

### Important Files
- `pom.xml`: Spring Boot starters + JPA.
- Sources: `src/main/java/` (controllers, services, entities).
- Resources: `src/main/resources/` (configs, `data.json`).

## 5. catfact — Cat Fact Service

### Project Type
Maven Spring Boot (Java 17).

### Description
A fun microservice that fetches random cat facts from a third-party API and persists them.

### Key Features
- `CatFactController` exposes REST endpoints.
- `CatFactService` handles external API calls.
- Uses `CatFactDto` for data structuring.

### How to Build/Run
```powershell
cd catfact
mvn spring-boot:run
```
- Endpoint: e.g., `GET /catfact` to fetch a fact.

### Important Files
- `pom.xml`: Spring Boot + web starter.
- Sources: `src/main/java/` (controller, service, DTO).
- Resources: `src/main/resources/application.properties` (API config).

## 6. cveapi — CVE API Integration

### Project Type
Maven Spring Boot (Java 17).

### Description
An API that integrates with the National Vulnerability Database (NVD) to fetch and manage CVE data.

### Key Features
- `NVDService` for REST calls to NVD API.
- `CVEController` exposes endpoints.
- Uses `NVDResponseDTO` for response mapping.

### How to Build/Run
```powershell
cd cveapi
mvn spring-boot:run
```
- Endpoints: e.g., `GET /cves/{id}`.

### Important Files
- `pom.xml`: Spring Boot + web/JPA.
- Sources: `src/main/java/` (controller, service, DTOs).
- Resources: `src/main/resources/` (configs).

## 7. employeeapi — Employee CRUD API

### Project Type
Maven Spring Boot (Java 17).

### Description
A standard CRUD REST API for managing employee records.

### Key Features
- Controller-Service-Repository pattern.
- Clean, scalable structure for entity management.

### How to Build/Run
```powershell
cd employeeapi
mvn spring-boot:run
```
- Endpoints: e.g., `POST /employees`, `GET /employees`.

### Important Files
- `pom.xml`: Spring Boot basics.
- Sources: `src/main/java/` (layers for Employee entity).

## 8. excelmysql — Excel to MySQL Importer

### Project Type
Maven Spring Boot (Java 17).

### Description
A web application for importing/exporting Person entity data between Excel files and MySQL database.

### Key Features
- `ExcelService` processes Excel files using Apache POI.
- Integrates with MySQL via JPA (configure in `application.properties`).

### How to Build/Run
```powershell
cd excelmysql
mvn spring-boot:run
```
- Ensure MySQL is running and configured.

### Important Files
- `pom.xml`: Spring Boot + POI + MySQL.
- Sources: `src/main/java/` (service, entity).
- Resources: `src/main/resources/application.properties` (DB config).

## 9. weatherapi — Weather Data Processor

### Project Type
Maven Java (Java 11+).

### Description
An early-stage project for processing weather data, including CSV handling.

### Key Features
- Basic structure for data loading/processing.
- Includes `testset.csv` for testing.

### How to Build/Run
```powershell
cd weatherapi
mvn package
```
- Extend with custom main for execution.

### Important Files
- `pom.xml`: Core Maven deps.
- Resources: `src/main/resources/testset.csv`.

## 10. Fil_sort_page — Student Management with Pagination, Filtering, Sorting

### Project Type
Maven Spring Boot (Java 17, MySQL).

### Description
Manages students with add/list operations, supporting pagination, sorting, and filtering. Auto-creates MySQL DB if absent.

### Key Features
- API for CRUD with query params for `page`, `size`, `sortBy`, `sortDir`, `name`, `age`.
- Uses Spring Data JPA for queries.

### How to Build/Run
```powershell
cd Fil_sort_page
mvn package
mvn spring-boot:run
```

### API Endpoints
| Method | Endpoint | Description | Parameters / Body |
|--------|----------|-------------|-------------------|
| POST | `/students` | Add student | JSON: `{ "name": "Alice", "age": 20, "email": "alice@mail.com" }` |
| GET | `/students` | List with pagination/sorting/filtering | Query: `?page=0&size=5&sortBy=age&sortDir=desc&name=Alice` |

### Example Requests
```http
# Add
POST /students
Content-Type: application/json
{
  "name": "Alice",
  "age": 20,
  "email": "alice@mail.com"
}

# List
GET /students?page=0&size=5&sortBy=age&sortDir=desc&name=Alice
```

### Important Files
- `pom.xml`: Spring Boot + JPA + MySQL.
- Sources: `src/main/java/` (Student entity, controller, repo).

## 11. XlSheet — Excel Sheet to MySQL via REST API

### Project Type
Maven Spring Boot (Java 17, MySQL).

### Description
Stores Microsoft Excel sheets in MySQL and provides REST API access. Uses Apache POI for Excel handling.

### Key Features
- Processes Excel with `XSSFWorkbook`.
- Endpoints for upload/storage/retrieval of student-like data.

### How to Build/Run
```powershell
cd XlSheet
mvn package
mvn spring-boot:run
```
- Ensure MySQL is configured.

### API Endpoints
- POST `/student/list`: Upload and process Excel to DB.

### Important Files
- `pom.xml`: Spring Boot + Apache POI + MySQL.
- Sources: `src/main/java/` (Excel processing logic).

## 12 . SpringSecirity - Basic Login and Signup page with role base 

## Project Type
ThymeLeaf , Maven Spring Boot (Java 17, MySQL).

### Description
Basic , Spring Security implementation along with Thymeleaf , to demonstrate implementation of Authorization and Authentication(session based)
### How to Build/Run
```powershell
cd XlSheet
mvn package
mvn spring-boot:run
```
- Ensure MySQL is configured.
### API Endpoints
   /login 

## 13 . STUDENT 
## Project type
Spring , Mysql

## DEscription 
 basic practice of spring , just get , post , delete

## 14 . JsonPagination
## Project type
Spring , Mysql , Json

## DEscription 
 parse the json file, store in Mysql , show endpoint , along pagination and filtering 

 ## 15. JsonToXmlApplication
## Project type
Spring , inout.json

## DEscription 
 parse the json file, convert to the xml , using recursive , object
 ```input.json
{
    "organization" : {
        "name" : "Securin",
        "type" : "Inc",
        "building_number" : 4,
        "floating" : -17.4,
        "null_test": null
    },
    "security_related" : true,
    "array_example0" : ["red", "green", "blue", "black"],
    "array_example1" : [1, "red", [{ "nested" : true}], { "obj" : false}]
}
{
	"samosa" :{
		"aloo" : "yellow",
		"maida" : "white"
	},
	"bolo jai mata di ": "jai mata di"
}
 ```
 ```output.xml
 <?xml version="1.0" encoding="UTF-8" standalone="no"?>
<object>
  <array name="array_example0">
    <string>red</string>
    <string>green</string>
    <string>blue</string>
    <string>black</string>
  </array>
  <array name="array_example1">
    <number>1</number>
    <string>red</string>
    <array>
      <object>
        <boolean name="nested">true</boolean>
      </object>
    </array>
    <object>
      <boolean name="obj">false</boolean>
    </object>
  </array>
  <object name="organization">
    <null name="null_test"/>
    <number name="floating">-17.4</number>
    <string name="name">Securin</string>
    <string name="type">Inc</string>
    <number name="building_number">4</number>
  </object>
  <boolean name="security_related">true</boolean>
</object>
 ```
  ## 15. GRAPHQL API
## Project type
Spring , GraphQL

## DEscription 
 create a basic GRAPHQL API in springboot
