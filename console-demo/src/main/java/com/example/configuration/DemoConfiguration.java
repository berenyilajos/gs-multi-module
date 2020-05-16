package com.example.configuration;

import bitronix.tm.BitronixTransactionManager;
import bitronix.tm.TransactionManagerServices;
import com.example.hello.impl.HelloWorld1;
import com.example.hello.impl.HelloWorld2;
import com.example.hello.interfaces.Hello;
import com.example.jta.BitronixJtaPlatform;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.TransactionManager;

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

    @Bean(name = "bitronixTransactionManager")
    @DependsOn
    public BitronixTransactionManager bitronixTransactionManager() throws Throwable {
        BitronixTransactionManager bitronixTransactionManager = TransactionManagerServices.getTransactionManager();
        bitronixTransactionManager.setTransactionTimeout(10000);
        BitronixJtaPlatform.setBitronixUserTransaction(bitronixTransactionManager);
        BitronixJtaPlatform.setBitronixTransactionManager(bitronixTransactionManager);
        return bitronixTransactionManager;
    }

    @Bean(name = "transactionManager")
    @DependsOn({"bitronixTransactionManager"})
    public PlatformTransactionManager transactionManager(TransactionManager bitronixTransactionManager) throws Throwable {
        return new JtaTransactionManager(bitronixTransactionManager);
    }

}
