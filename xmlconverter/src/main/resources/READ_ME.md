XML to Protobuf Converter
This project is a Spring Boot command-line application that converts XML files into Google Protocol Buffer (Protobuf) messages. The conversion logic is generic and can work with any valid XML and a corresponding Protobuf schema.

This project was created to fulfill the requirements of the "Assessment-4-XML-to_protobuf" assignment.

Design Motivations
The core of this application is a generic, reflection-based converter. The main design goal was to create a solution that was not tied to a specific XML structure or Protobuf schema.

Generic Logic: The converter (XMLToProtobufConverterImpl.java) uses Protobuf's Descriptor API to dynamically inspect the target message structure at runtime. It maps XML element and attribute names to Protobuf field names. This allows the same code to convert any XML file to any Protobuf message, provided their structures are compatible.

Spring Boot Framework: We chose Spring Boot to create a modern, self-contained application. It simplifies dependency management and provides a robust CommandLineRunner interface, which is perfect for executing the conversion logic on startup.

Maven for Build Automation: The project uses Apache Maven to manage dependencies and automate the build process. A key part of the build is the protobuf-maven-plugin, which automatically finds .proto files and generates the corresponding Java source code, integrating seamlessly into the build lifecycle.

Included Auxiliary Files
src/main/resources/sample1.xml: The primary sample file used for testing and verification, containing a full employee record.

src/main/resources/sample2.xml: A second sample file with a partial employee record, used to verify that the converter can handle missing XML elements.

src/main/proto/employee.proto: The Protobuf schema definition that corresponds to the sample XML files.

The program reads these sample files, converts them, and saves the text representation of the resulting Protobuf messages into the output/ directory.

Libraries Used
Spring Boot: Provides the application framework.

URL: https://spring.io/projects/spring-boot

Google Protocol Buffers (protobuf-java): The runtime library for using Protobuf in Java.

URL: https://github.com/protocolbuffers/protobuf

protobuf-maven-plugin: A Maven plugin to automatically compile .proto files into Java classes.

URL: https://www.xolstice.org/protobuf-maven-plugin/

How to Build the Project
You must have Java 17 (or higher) and Apache Maven installed.

Open a terminal or command prompt.

Navigate to the root directory of the project.

Run the following Maven command to build the application:

Bash

mvn clean install
This command will compile the .proto schema, compile the Java source code, and package the application into an executable JAR file in the target/ directory.

How to Run the Application
There are two ways to run the application after building it:

1. Using java -jar (Recommended)
This method runs the final packaged application.

Bash

java -jar target/xml-converter-0.0.1-SNAPSHOT.jar
2. Using the Spring Boot Maven Plugin
This method is useful for development and runs the application without packaging it first.

Bash

mvn spring-boot:run
Both commands will execute the program. The application will create an output/ directory in the project's root folder and save the conversion results into employee1_output.txt and employee2_output.txt. You will also see confirmation messages printed to the console.