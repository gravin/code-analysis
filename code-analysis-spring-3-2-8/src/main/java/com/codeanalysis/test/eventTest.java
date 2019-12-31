package com.codeanalysis.test;

import com.codeanalysis.TestEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.GregorianCalendar;
import java.util.Locale;

public class eventTest {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/eventTest.xml");
        TestEvent event = new TestEvent("", "hello world");
        ctx.publishEvent(event);
    }
}