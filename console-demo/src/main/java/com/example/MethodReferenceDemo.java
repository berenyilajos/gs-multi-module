package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

class MyManager {
    List<String> data = Arrays.asList("Jane", "Ben", "Christine", "John", "Martha");

    public void printDataWithFilterAndParser(Predicate<String> filter, Parser parser) {
        data.stream()
                .filter(filter)
                .forEach(d -> MyPrinter.print(parser.parse(d)));
    }

    public void printData(Predicate<String> filter, Function<String, String> function) {
        data.stream()
                .filter(filter)
                .forEach(d -> MyPrinter.print(function.apply(d)));
    }

}

interface Parser {
    String parse(String str);
}

class StringParser {
    public static String convert(String s) {
        if (s.length() <= 3)
            s = s.toUpperCase();
        else
            s = s.toLowerCase();

        return s;
    }
}

class MyPrinter {
    public static void print(String str) {
        System.out.println(str);
    }
}

public class MethodReferenceDemo {

    public static void main(String[] args) {
        MyManager manager = new MyManager();
        manager.printDataWithFilterAndParser(s -> s.length() < 5, String::toUpperCase);
        System.out.println("===============");
        manager.printDataWithFilterAndParser(s -> s.length() > 3, String::toLowerCase);
        System.out.println("===============");
        manager.printDataWithFilterAndParser(s -> true, StringParser::convert);

        System.out.println("===============");
        System.out.println("===============");

        manager.printData(s -> s.length() < 5, String::toUpperCase);
        System.out.println("===============");
        manager.printData(s -> s.length() > 3, String::toLowerCase);
        System.out.println("===============");
        manager.printData(s -> true, StringParser::convert);
    }

}
