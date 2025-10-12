package com.securin.converters;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;

public class JsonToXmlConverter implements XMLJSONConverterI {

    @Override
    public void convertJSONtoXML(File inputJson, File outputXml) {
        try {
            FileReader reader = new FileReader(inputJson);
            try {
            	//lexical scanner 
                JSONTokener tokener = new JSONTokener(reader);
                Object jsonValue = tokener.nextValue();

                if (!(jsonValue instanceof JSONObject || jsonValue instanceof JSONArray)) {
                    throw new IllegalArgumentException("Top-level element must be a JSON object or array.");
                }
                //abstract class  -- xml parser
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                //parse existing xml file to objects
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                //new fresh document
                Document doc = docBuilder.newDocument();
                

                buildXml(doc, doc, null, jsonValue);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

                DOMSource source = new DOMSource(doc);
                FileWriter writer = new FileWriter(outputXml);
                try {
                    StreamResult result = new StreamResult(writer);
                    transformer.transform(source, result);
                } finally {
                    writer.close();
                }
            } finally {
                reader.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert JSON to XML", e);
        }
    }

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
                Object val = jsonObject.get(currentKey);
                buildXml(doc, objectElement, currentKey, val);
            }
        } else if (value instanceof JSONArray) {
            Element arrayElement = doc.createElement("array");
            if (key != null) {
                arrayElement.setAttribute("name", key);
            }
            appendNode(parentNode, arrayElement);

            JSONArray jsonArray = (JSONArray) value;
            for (int i = 0; i < jsonArray.length(); i++) {
                Object item = jsonArray.get(i);
                buildXml(doc, arrayElement, null, item);
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

    private void appendNode(Object parent, Element child) {
        if (parent instanceof Document) {
            ((Document) parent).appendChild(child);
        } else {
            ((Element) parent).appendChild(child);
        }
    }

    private String getType(Object value) {
        if (value instanceof String) {
            return "string";
        } else if (value instanceof Number) {
            return "number";
        } else if (value instanceof Boolean) {
            return "boolean";
        }
        return "unknown";
    }
}