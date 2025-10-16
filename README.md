## Practise-Spring — Workspace Overview

This repository is a collection of small Java/Maven projects (mostly Spring Boot demos and simple converters) grouped together in a single workspace. The root contains multiple independent Maven projects — build and run each project from its own directory (where the project's `pom.xml` is located).

This README collects the important information you need to build, run and explore each module. It also calls out known issues and quick suggestions for improvements.

## Projects (quick index)

- `assessment1` — json-to-xml converter (artifactId: `json-to-xml-converter`, Java 8)
- `xmlconverter` — XML → Protobuf converter (Spring Boot, Java 17, uses protobuf-maven-plugin)
- `JSON_XML_VICE` — JSON ↔ XML utilities (Jackson-based)
- `jsonapi` — Spring Boot demo app (data loader, Java 17)
- `catfact` — Spring Boot demo that fetches a cat fact and persists it
- `cveapi`, `employeeapi`, `excelmysql`, `weatherapi` — additional Spring Boot demo modules

There are also `HELP.md` or `READ_ME.md` files in many modules with module-specific notes — consult them where present.

## Build (per-project)

Each subproject is a standalone Maven project. From PowerShell, change into the project directory (the directory that contains `pom.xml`) and run the usual Maven commands.

PowerShell examples:

```powershell
# build
mvn -f .\pom.xml clean package

# run Spring Boot app (if project is a Spring Boot project)
mvn -f .\pom.xml spring-boot:run

# run the produced jar (if the build produced an executable jar)
java -jar .\target\<artifact>-<version>.jar
```

Notes:
- `assessment1` uses the Maven Assembly plugin to create a `jar-with-dependencies`. The `pom.xml` sets the `mainClass` to `com.securin.converters.Cli` so run that jar with two args: input JSON and target XML.
- `xmlconverter` is a Spring Boot project and also invokes the protobuf-maven-plugin to compile .proto definitions when present. Use `mvn spring-boot:run` or run the produced jar.

## Important files & entry points

Open these locations when you want to inspect core behavior:

- `assessment1/pom.xml` — declares `json` dependency and assembly plugin (main class `com.securin.converters.Cli`).
- `assessment1/READ_ME.md` — local instructions for building and running the converter.
- `xmlconverter/pom.xml` — Spring Boot + protobuf plugin configuration.
- `JSON_XML_VICE/pom.xml` — uses Jackson (xml + json modules) for conversions.
- `jsonapi/pom.xml`, `catfact/pom.xml`, `cveapi/pom.xml`, `employeeapi/pom.xml`, `excelmysql/pom.xml`, `weatherapi/pom.xml` — Spring Boot demo projects.

If you need the exact Java source locations look under `src/main/java/` in each module; many projects also have `src/main/resources` with example inputs/configs.

## Example: run the assessment1 converter (PowerShell)

1. Build the module:

```powershell
cd .\assessment1
mvn clean package
```

2. Run the produced jar (the assembly creates `json-to-xml-converter-1.0.0-jar-with-dependencies.jar`):

```powershell
java -jar .\target\json-to-xml-converter-1.0.0-jar-with-dependencies.jar .\example_input.json .\output.xml
```

Replace `example_input.json` and `output.xml` with your input/output paths.

## Known issues & portability notes

- Hard-coded absolute paths: at least one module (`jsonapi`) contains code that reads a file using an absolute path inside `src/main/resources` (the original code used an absolute Windows path). This prevents portable builds and should be changed to use classpath resource loading (for example `getResourceAsStream` or Spring's `ClassPathResource`).
- Mixed Java versions: some modules target Java 8 while others use Java 17. Ensure your build environment has the correct JDK(s) installed. For building Java 17 modules, point `JAVA_HOME` to a JDK 17 installation.
- Missing runtime configuration: Spring Boot modules that use a DB (MySQL) expect runtime DB connection configuration in `application.properties` or environment variables. If you don't have a DB running, comment out the JPA usage or use an embedded DB profile for development.

## Suggested improvements (low-risk)

- Replace absolute file paths with classpath resource loading and use `Paths`/`Files` or Spring `ResourceLoader` for portability.
- Standardize Java version across projects where practical (use Java 17 if you need Spring Boot 3.x features).
- Add small unit tests for conversion logic (happy path + 1-2 edge cases) under `src/test/java` for converter projects.
- Add README files into each module (if absent) containing sample input and expected output. `assessment1` already contains a helpful `READ_ME.md`.
- Use timestamp or UUID based output filenames instead of random counters to avoid collisions in `JSON_XML_VICE`.

## How I collected this information

I inspected each module's `pom.xml` and the available `HELP.md`/`READ_ME.md` files to determine artifact IDs, key dependencies and build plugins (notably Spring Boot and protobuf plugins). Use the listed file locations above to examine the source implementations.

## Next steps you might ask me to do

- Create or update per-module `README.md` files with sample inputs and run examples.
- Fix the absolute path in `jsonapi` so it loads `data.json` from the classpath and add a small unit test.
- Consolidate the workspace into a multi-module parent `pom.xml` if you want to build all projects with one command.

If you'd like, I can commit this `README.md` to the repository now and (optionally) implement one of the suggested fixes (I recommend starting with the `jsonapi` classpath fix). Which would you prefer next?

---

Generated on: 2025-10-12
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
### Student Management (Fil_sort_page)

**Type:** Spring Boot, Java 17, MySQL  

**Purpose:**  
Manage students with the ability to **add**, **list**, **paginate**, **sort**, and **filter** records.  
Database auto-creates if not present.

**Black-box Usage:**

**API Endpoints:**

| Method | Endpoint       | Description                                | Parameters / Body                                              |
|--------|----------------|--------------------------------------------|----------------------------------------------------------------|
| POST   | /students      | Add a new student                          | JSON body: `{ "name": "Alice", "age": 20, "email": "alice@mail.com" }` |
| GET    | /students      | Retrieve students (with pagination, sorting, filtering) | Query params: `page`, `size`, `sortBy`, `sortDir`, `name`, `age` |

**Example Requests:**

```http
# Add a student
POST /students
Content-Type: application/json
{
  "name": "Alice",
  "age": 20,
  "email": "alice@mail.com"
}

# Get first page (5 students per page), sort by age descending, filter by name "Alice"
GET /students?page=0&size=5&sortBy=age&sortDir=desc&name=Alice

```
mvn package
```
