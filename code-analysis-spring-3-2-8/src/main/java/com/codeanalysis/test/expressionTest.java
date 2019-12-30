package com.codeanalysis.test;

import com.codeanalysis.MyTestBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class expressionTest {
    public static void main(String[] args) {
//        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("spring/beanFactoryTest.xml"));
        ApplicationContext bf=new ClassPathXmlApplicationContext("spring/expressionTest.xml");
        MyTestBean bean= (MyTestBean) bf.getBean("myTestBean");
        System.out.println(bean.getTestStr());
    }
}