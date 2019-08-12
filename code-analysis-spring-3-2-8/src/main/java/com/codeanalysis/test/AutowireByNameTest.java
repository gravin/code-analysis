package com.codeanalysis.test;

import com.codeanalysis.HelloWorldTest;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class AutowireByNameTest {
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext-test.xml");
        BeanFactory bf=new XmlBeanFactory(new ClassPathResource("spring/applicationContext-test.xml"));
        HelloWorldTest test = (HelloWorldTest) bf.getBean("helloWorldTest");
        test.doSay();
        //打印结果：Hello World
    }
}
