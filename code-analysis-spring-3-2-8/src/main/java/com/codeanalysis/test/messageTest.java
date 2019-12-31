package com.codeanalysis.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.GregorianCalendar;
import java.util.Locale;

public class messageTest {
    public static void main(String[] args) {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("spring/messageTest.xml");
        String test = ctx.getMessage("test", new Object[]{"hello", new GregorianCalendar().getTime()}, Locale.US);
        System.out.println(test);
    }
}