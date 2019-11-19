package com.example.jpademo.parser;

import com.example.jpademo.parser.interfaces.Parser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class JsonParser<T> implements Parser<T> {

    private static final ObjectMapper mapper = new ObjectMapper();
    private Class<T> parserClass;

    public JsonParser(Class<T> parserClass) {
        this.parserClass = parserClass;
    }

    public T parse(InputStream input) throws Exception {
        return parseJson(input);
    }

    public void write(T obj, OutputStream output) throws Exception {
        writeJson(obj, output);
    }

    private T parseJson(InputStream input) throws IOException {
        return mapper.readValue(input, getParserClass());
    }

    private void writeJson(T obj, OutputStream output) throws IOException {
        mapper.writeValue(output, obj);
    }

    public Class<T> getParserClass() {
        return parserClass;
    }

    public String getExtension() {
        return ".json";
    }
}
