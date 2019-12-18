package com.codeanalysis.test;

import com.codeanalysis.MyTestBean;
import com.codeanalysis.TestChangeMethod;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class ChangeMethodTest {
    public static void main(String[] args) {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("spring/replaceMethodTest.xml"));
        TestChangeMethod bean= (TestChangeMethod) bf.getBean("testChangeMethod");
        bean.changeMe();
    }
}