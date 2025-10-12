JSON to XML Converter

This is a small Java program that changes a JSON file into an XML file.

Libraries Used

org.json:json:20140107 – Helps the program read and work with JSON data.

GitHub link: https://github.com/stleary/JSON-java

How to Build the Program

1.Make sure you have Java 7 or newer installed.

2.Make sure Apache Maven is installed.

3.Open your command prompt (Windows) or terminal (Mac/Linux).

4.Go to the main folder of this project (where the pom.xml file is).

Run this command:
mvn clean package

After this, a folder named target will be created.

Inside the target folder, you will see:

json-to-xml-converter-1.0.0-jar-with-dependencies.jar

This is the program file you will run.

How to Run the Program

1.Open command prompt or terminal.

2.Run the program using this command:

java -jar target/json-to-xml-converter-1.0.0-jar-with-dependencies.jar input.json output.xml


Replace input.json with the name of your JSON file.

Replace output.xml with the name you want for your XML file.