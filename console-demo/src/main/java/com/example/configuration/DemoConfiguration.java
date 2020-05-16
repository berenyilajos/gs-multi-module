package com.example.configuration;

import com.example.hello.impl.HelloWorld1;
import com.example.hello.impl.HelloWorld2;
import com.example.hello.interfaces.Hello;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "com.example")
@Configuration
public class DemoConfiguration {

    @Bean(name="helloWorld11")
//    @Primary
    public Hello helloWorld11() {
        return new HelloWorld1("World11");
    }

    @Bean(name="helloWorld12")
    public Hello helloWorld12() {
        return new HelloWorld1("World12");
    }

    @Bean()
    public Hello helloWorld21() {
        return new HelloWorld2("World21");
    }

    @Bean()
    @Qualifier("helloWorld222")
    public Hello helloWorld22() {
        return new HelloWorld2("World22");
    }

}
