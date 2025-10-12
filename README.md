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

## Overview
This repository contains multiple Spring Boot projects built for practice, learning, and experimentation with various Java, Spring, and database concepts. Each subdirectory represents a standalone project demonstrating different features, APIs, or integrations.

---

## Projects

### 1. JSON_XML_VICE
- **Description:** Demonstrates conversion between JSON and XML files. Likely includes file handling, parsing, and transformation using Spring Boot.
- **Skills Covered:** JSON processing, XML handling, Java I/O, Spring Boot services.

### 2. assessment1
- **Description:** Likely a small Spring Boot application for practice or assessment purposes.
- **Skills Covered:** CRUD operations, REST APIs, Spring Boot basics.

### 3. catfact
- **Description:** Provides random cat facts, likely via a REST API.
- **Skills Covered:** RESTful API creation, JSON responses, basic controller-service-repository structure.

### 4. cveapi
- **Description:** Interfaces with a CVE (Common Vulnerabilities and Exposures) API to fetch security vulnerabilities.
- **Skills Covered:** External API integration, REST calls, JSON processing.

### 5. employeeapi
- **Description:** Manages employee data with CRUD operations.
- **Skills Covered:** Spring Data JPA, REST APIs, MySQL integration.

### 6. excelmysql
- **Description:** Demonstrates reading/writing Excel files and integrating them with a MySQL database.
- **Skills Covered:** Apache POI for Excel, MySQL CRUD, data persistence.

### 7. firstapp
- **Description:** The first Spring Boot project in this repository; likely covers fundamental Spring Boot setup and configurations.
- **Skills Covered:** Spring Boot initialization, application properties, basic REST endpoints.

### 8. jsonapi
- **Description:** Provides JSON-based APIs for various functionalities.
- **Skills Covered:** REST API design, JSON serialization/deserialization, service layers.

### 9. weatherapi
- **Description:** Provides weather information via an API, possibly using external API calls.
- **Skills Covered:** REST client integration, JSON handling, Spring Boot services.

---

## Getting Started

### Prerequisites
- Java 17 or later
- Maven
- MySQL (if using projects with database integration)

### Running a Project
1. Clone the repository:
   ```bash
   git clone https://github.com/jitabhsin/Practice-Spring.git
