package com.codeanalysis;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldTest {

    private Integer num;

    private HelloWorld helloWorld;

    public void setHelloWorld(HelloWorld helloWorld) {
        this.helloWorld = helloWorld;
    }

    public HelloWorldTest(Integer num, HelloWorld helloWorld) {
        this.num = num;
        this.helloWorld = helloWorld;
    }

    public HelloWorldTest(Integer num) {
        this.num = num;
    }

    public void doSay() {
        helloWorld.say();
    }
}