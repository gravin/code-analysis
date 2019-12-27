package com.codeanalysis.test;

import com.codeanalysis.AutowireTestA;
import com.codeanalysis.MyTestBean;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class AutowireTest {
    public static void main(String[] args) {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("spring/autowireTest.xml"));
//        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring/beanFactoryTest.xml");
        AutowireTestA bean= (AutowireTestA) bf.getBean("autowireTestA");
        System.out.println(bean);
    }
}