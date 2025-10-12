package com.example.xmlconverter.converter;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

@Component
public class XMLToProtobufConverterImpl implements XMLToProtobufConverter {

    @Override
    public Message convert(File xmlFile, Message defaultInstance) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            Document doc = docBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            Message.Builder messageBuilder = defaultInstance.newBuilderForType();
            populateBuilder(doc.getDocumentElement(), messageBuilder);
            return messageBuilder.build();

        } catch (Exception e) {
            throw new RuntimeException("Failed to convert XML", e);
        }
    }

    private void populateBuilder(Node xmlNode, Message.Builder protoBuilder) {
        Descriptors.Descriptor protoDescriptor = protoBuilder.getDescriptorForType();

        if (xmlNode.hasAttributes()) {
            NamedNodeMap attributes = xmlNode.getAttributes();
            for (int i = 0; i < attributes.getLength(); i++) {
                Node attr = attributes.item(i);
                Descriptors.FieldDescriptor field = protoDescriptor.findFieldByName(attr.getNodeName());
                if (field != null) {
                    Object value = parseValue(attr.getNodeValue(), field);
                    protoBuilder.setField(field, value);
                }
            }
        }

        NodeList childNodes = xmlNode.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                Descriptors.FieldDescriptor field = protoDescriptor.findFieldByName(childNode.getNodeName());
                if (field != null) {
                    Object value = getValueForField(childNode, field, protoBuilder);
                    if (field.isRepeated()) {
                        protoBuilder.addRepeatedField(field, value);
                    } else {
                        protoBuilder.setField(field, value);
                    }
                }
            }
        }
    }
    
    private Object getValueForField(Node xmlNode, Descriptors.FieldDescriptor field, Message.Builder parentBuilder) {
        if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            Message.Builder subBuilder = parentBuilder.newBuilderForField(field);
            populateBuilder(xmlNode, subBuilder);
            return subBuilder.build();
        } else {
            return parseValue(xmlNode.getTextContent(), field);
        }
    }

    private Object parseValue(String value, Descriptors.FieldDescriptor field) {
        return switch (field.getType()) {
            case INT32 -> Integer.parseInt(value);
            case INT64 -> Long.parseLong(value);
            case FLOAT -> Float.parseFloat(value);
            case DOUBLE -> Double.parseDouble(value);
            case BOOL -> Boolean.parseBoolean(value);
            case STRING -> value;
            default -> throw new IllegalArgumentException("Unsupported field type: " + field.getType());
        };
    }
}