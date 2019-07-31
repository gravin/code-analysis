package com.codeanalysis.test;

import com.codeanalysis.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class CustomTagTest {
    public static void main(String[] args) {
//        ApplicationContext bf = new ClassPathXmlApplicationContext("spring/userTag.xml");
        BeanFactory bf=new XmlBeanFactory(new ClassPathResource("spring/userTag.xml"));
        User user = (User) bf.getBean("testbean");
        System.out.println(user.getUserName() + "," + user.getEmail());
    }
}
