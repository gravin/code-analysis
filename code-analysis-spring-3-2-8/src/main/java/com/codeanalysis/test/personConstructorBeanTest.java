package com.codeanalysis.test;

import com.codeanalysis.MyTestBean;
import com.codeanalysis.Person;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import java.security.Permission;

public class personConstructorBeanTest {
    public static void main(String[] args) {
        BeanFactory bf=new XmlBeanFactory(new ClassPathResource("spring/personTest.xml"));
        Person bean= (Person) bf.getBean("person");
        System.out.println(bean.toString());
    }
}