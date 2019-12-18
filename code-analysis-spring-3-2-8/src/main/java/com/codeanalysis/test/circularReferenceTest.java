package com.codeanalysis.test;

import com.codeanalysis.MyBeanPostProcessor;
import com.codeanalysis.TestA;
import com.codeanalysis.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class circularReferenceTest {
    public static void main(String[] args) {
//        ApplicationContext bf = new ClassPathXmlApplicationContext("spring/userTag.xml");
        BeanFactory bf=new XmlBeanFactory(new ClassPathResource("spring/circularReferenceTest.xml"));
        ((XmlBeanFactory) bf).addBeanPostProcessor(new MyBeanPostProcessor());
        TestA testA = (TestA) bf.getBean("testA");
        System.out.println(testA);
    }
}
