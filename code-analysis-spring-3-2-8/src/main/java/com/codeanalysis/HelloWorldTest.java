package com.codeanalysis;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldTest {

    private HelloWorld helloWorld;

    public void setHelloWorldEnglish(HelloWorld helloWorld) {
        this.helloWorld = helloWorld;
    }

    public void doSay(){
        helloWorld.say();
    }
}