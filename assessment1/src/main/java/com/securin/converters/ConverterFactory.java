package com.securin.converters;

/**
 * Factory class for creating instances of {@link XMLJSONConverterI}.
 */
public final class ConverterFactory {

    /**
     * You should implement this method having it return your version of
     * {@link com.securin.converters.XMLJSONConverterI}.
     *
     * @return {@link com.securin.converters.XMLJSONConverterI} implementation you
     *         created.
     */
    public static final XMLJSONConverterI createXMLJSONConverter() {
        // This is the implementation.
        // Instead of throwing an error, we now return a new instance of our converter
        // class.
        return new JsonToXmlConverter();
    }
}
