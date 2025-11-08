package com.example.JsonToXmlApplication.service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import jakarta.annotation.PostConstruct;

@Service
public class JsonToXmlService {

    /**
     * This method executes automatically after Spring initializes the service.
     * It reads JSON from resources and writes the converted XML inside resources folder.
     */
    @PostConstruct
    public void executeConversion() {
        try {
            // Load input.json from resources
            File inputJson = new ClassPathResource("input.json").getFile();

            // Output XML inside resources folder
            File resourcesFolder = new ClassPathResource("").getFile(); // points to src/main/resources
            File outputXml = new File(resourcesFolder, "output.xml");

            // Call conversion method
            convertJSONtoXML(inputJson, outputXml);

            // Print absolute path of the output file
            System.out.println("Conversion completed! Output file: " + outputXml.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts a JSON file to an XML file
     */
    public void convertJSONtoXML(File inputJson, File outputXml) {
        try (FileReader reader = new FileReader(inputJson)) {
            // Parse JSON
            JSONTokener tokener = new JSONTokener(reader);
            Object jsonValue = tokener.nextValue();

            if (!(jsonValue instanceof JSONObject || jsonValue instanceof JSONArray)) {
                throw new IllegalArgumentException("Top-level element must be JSON object or array.");
            }

            // Create XML document
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Build XML recursively
            buildXml(doc, doc, null, jsonValue);

            // Write XML to file
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            try (FileWriter writer = new FileWriter(outputXml)) {
                transformer.transform(new DOMSource(doc), new StreamResult(writer));
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to convert JSON to XML", e);
        }
    }

    /**
     * Recursively converts JSON to XML elements
     */
    private void buildXml(Document doc, Object parentNode, String key, Object value) {
        if (value instanceof JSONObject) {
            Element objectElement = doc.createElement("object");
            if (key != null) {
				objectElement.setAttribute("name", key);
			}
            appendNode(parentNode, objectElement);

            JSONObject jsonObject = (JSONObject) value;
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String currentKey = keys.next();
                buildXml(doc, objectElement, currentKey, jsonObject.get(currentKey));
            }

        } else if (value instanceof JSONArray) {
            Element arrayElement = doc.createElement("array");
            if (key != null) {
				arrayElement.setAttribute("name", key);
			}
            appendNode(parentNode, arrayElement);

            JSONArray jsonArray = (JSONArray) value;
            for (int i = 0; i < jsonArray.length(); i++) {
                buildXml(doc, arrayElement, null, jsonArray.get(i));
            }

        } else if (JSONObject.NULL.equals(value)) {
            Element nullElement = doc.createElement("null");
            if (key != null) {
				nullElement.setAttribute("name", key);
			}
            appendNode(parentNode, nullElement);

        } else {
            String type = getType(value);
            Element element = doc.createElement(type);
            if (key != null) {
				element.setAttribute("name", key);
			}
            element.appendChild(doc.createTextNode(String.valueOf(value)));
            appendNode(parentNode, element);
        }
    }

    /**
     * Attach child element to parent node
     */
    private void appendNode(Object parent, Element child) {
        if (parent instanceof Document) {
			((Document) parent).appendChild(child);
		} else {
			((Element) parent).appendChild(child);
		}
    }

    /**
     * Determine XML element type based on JSON value
     */
    private String getType(Object value) {
        if (value instanceof String) {
			return "string";
		} else if (value instanceof Number) {
			return "number";
		} else if (value instanceof Boolean) {
			return "boolean";
		} else {
			return "unknown";
		}
    }
}
