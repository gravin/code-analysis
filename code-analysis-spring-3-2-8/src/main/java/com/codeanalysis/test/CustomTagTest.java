package com.codeanalysis.test;

import com.codeanalysis.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CustomTagTest {
    public static void main(String[] args) {
        ApplicationContext bf = new ClassPathXmlApplicationContext("spring/userTag.xml");
        User user = (User) bf.getBean("testbean");
        System.out.println(user.getUserName() + "," + user.getEmail());
    }
}
