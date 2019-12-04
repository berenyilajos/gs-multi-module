package com.example.demo.parser.interfaces;

import java.io.InputStream;
import java.io.OutputStream;

public interface Parser<T> {

    T parse(InputStream input) throws Exception;

    void write(T obj, OutputStream output) throws Exception;

    String getExtension();

}
