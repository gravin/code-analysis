package com.codeanalysis.test;

import com.codeanalysis.MyTestBean;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class FirstBeanTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring/beanFactoryTest.xml");
        MyTestBean bean= (MyTestBean) applicationContext.getBean("myTestBean");
        System.out.println(bean.getTestStr());
    }
}