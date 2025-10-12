package com.example.xmlconverter.converter;

import com.google.protobuf.Message;
import java.io.File;

public interface XMLToProtobufConverter {
    Message convert(File xmlFile, Message defaultInstance);
}