package com.codeanalysis;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldEnglishImpl implements HelloWorld {
    @Override
    public void say() {
        System.out.println("Hello World");
    }
}
