package com.example.hello.abstr;

import com.example.hello.interfaces.Hello;

public abstract class HelloWorld implements Hello {

    private String name;

    public HelloWorld(String name) {
        this.name = name;
    }

    @Override
    public String hello() {
        return "Hello " + name + "!";
    }
}
