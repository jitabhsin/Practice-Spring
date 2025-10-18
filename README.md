╔════════════════════════════════════════════════════════════════════════════════════════════════╗
║                                      PRACTISE-SPRING WORKSPACE OVERVIEW                         ║
╠════════════════════════════════════════════════════════════════════════════════════════════════╣
║ This repository is a collection of small Java/Maven projects (mostly Spring Boot demos         ║
║ and simple converters) grouped together in a single workspace. Each subproject is independent  ║
║ with its own pom.xml.                                                                          ║
║                                                                                                ║
║ README collects build/run instructions, known issues, and suggested improvements.              ║
╠════════════════════════════════════════════════════════════════════════════════════════════════╣
║ PROJECTS QUICK INDEX                                                                           ║
║ ---------------------------------------------------------------------------------------------- ║
║ - assessment1      : json-to-xml converter (Java 8, artifactId: json-to-xml-converter)        ║
║ - xmlconverter     : XML → Protobuf converter (Spring Boot, Java 17)                           ║
║ - JSON_XML_VICE    : JSON ↔ XML utilities (Jackson-based)                                      ║
║ - jsonapi          : Spring Boot demo app (data loader, Java 17)                               ║
║ - catfact          : Spring Boot demo fetching and persisting cat facts                         ║
║ - cveapi           : Spring Boot demo (CVE API)                                                ║
║ - employeeapi      : Spring Boot demo (Employee CRUD API)                                      ║
║ - excelmysql       : Excel → MySQL data importer                                              ║
║ - weatherapi       : Spring Boot demo for weather data                                         ║
╠════════════════════════════════════════════════════════════════════════════════════════════════╣
║ BUILD INSTRUCTIONS (Per-Project)                                                              ║
║ ---------------------------------------------------------------------------------------------- ║
║ Each project is standalone Maven. From the terminal:                                           ║
║                                                                                                ║
║ > cd <project_dir>                                                                              ║
║ > mvn clean package                                                                              ║
║ > mvn spring-boot:run   (for Spring Boot projects)                                              ║
║ > java -jar target/<artifact>-<version>.jar (for executable jars)                              ║
║                                                                                                ║
║ Notes:                                                                                         ║
║ - assessment1 uses Maven Assembly plugin; run jar with JSON input and XML output.              ║
║ - xmlconverter runs Spring Boot + protobuf compilation.                                        ║
╠════════════════════════════════════════════════════════════════════════════════════════════════╣
║ IMPORTANT FILES & ENTRY POINTS                                                                 ║
║ ---------------------------------------------------------------------------------------------- ║
║ assessment1/pom.xml      : JSON dependency + assembly plugin (mainClass: com.securin.converters.Cli) ║
║ assessment1/READ_ME.md   : Local build/run instructions                                       ║
║ xmlconverter/pom.xml     : Spring Boot + protobuf plugin config                                 ║
║ JSON_XML_VICE/pom.xml    : Jackson XML + JSON conversions                                      ║
║ jsonapi/.../pom.xml      : Standard Spring Boot demos                                          ║
║ Source code: src/main/java/, resources: src/main/resources                                     ║
╠════════════════════════════════════════════════════════════════════════════════════════════════╣
║ EXAMPLE: Run assessment1 converter                                                            ║
║ ---------------------------------------------------------------------------------------------- ║
║ cd ./assessment1                                                                               ║
║ mvn clean package                                                                               ║
║ java -jar ./target/json-to-xml-converter-1.0.0-jar-with-dependencies.jar example_input.json    ║
║ ./output.xml                                                                                   ║
╠════════════════════════════════════════════════════════════════════════════════════════════════╣
║ KNOWN ISSUES & PORTABILITY NOTES                                                              ║
║ ---------------------------------------------------------------------------------------------- ║
║ - Hard-coded paths (jsonapi): use classpath resource loading                                    ║
║ - Mixed Java versions: Java 8 + Java 17; ensure proper JDKs installed                            ║
║ - Missing DB config: Spring Boot modules using MySQL need application.properties/env vars      ║
╠════════════════════════════════════════════════════════════════════════════════════════════════╣
║ SUGGESTED IMPROVEMENTS                                                                         ║
║ ---------------------------------------------------------------------------------------------- ║
║ - Replace absolute paths with classpath or Spring ResourceLoader                                 ║
║ - Standardize Java version (prefer Java 17 for Spring Boot 3.x)                                 ║
║ - Add small unit tests for converters (happy + edge cases)                                      ║
║ - Add README.md in modules with sample input/output                                              ║
║ - Use timestamp/UUID for output files to avoid collisions                                        ║
╠════════════════════════════════════════════════════════════════════════════════════════════════╣
║ PROJECT DETAILS                                                                                ║
║ ---------------------------------------------------------------------------------------------- ║
║ 1) xmlconverter  : XML → Protobuf, command-line, uses protoc, sample XML → serialized protobuf   ║
║ 2) weatherapi    : Spring Boot, early-stage, CSV data processing                                  ║
║ 3) JSON_XML_VICE : JSON ↔ XML conversion service using Jackson                                    ║
║ 4) jsonapi       : REST CRUD API, User entity, Spring Data JPA                                     ║
║ 5) excelmysql    : Excel ↔ MySQL import/export, Person entity                                      ║
║ 6) cveapi        : CVE integration API, NVD REST calls, DTOs                                      ║
║ 7) employeeapi   : Employee CRUD API, standard Controller-Service-Repository                       ║
║ 8) catfact       : Fun API fetching random cat facts                                              ║
║ 9) assessment1   : CLI JSON → XML converter, uses ConverterFactory                                 ║
║ 10) Fil_sort_page: Student management (add/list/paginate/sort/filter)                             ║
║ 11) XlSheet      : MS Excel → MySQL storage + REST API using Apache POI                             ║
╠════════════════════════════════════════════════════════════════════════════════════════════════╣
║ END OF WORKSPACE OVERVIEW                                                                      ║
╚════════════════════════════════════════════════════════════════════════════════════════════════╝
