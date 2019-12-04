package com.example.demo.parser;

import com.example.demo.parser.interfaces.Parser;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public abstract class XmlParser<T> implements Parser<T> {

    private static final XStream xStream = new XStream(new DomDriver());

    public T parse(InputStream input) throws Exception {
        return parseXml(input);
    }

    public void write(T obj, OutputStream output) throws Exception {
        writeXml(obj, output);
    }

    private static <T> T parseXml(InputStream in) throws Exception {
        return (T)xStream.fromXML(in);
    }

    private static <T> void writeXml(T obj, OutputStream out) throws Exception {
        out.write(xStream.toXML(obj).getBytes(StandardCharsets.UTF_8));
    }

    public String getExtension() {
        return ".xml";
    }

}
