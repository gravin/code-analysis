package com.codeanalysis.test;

import com.codeanalysis.MyTestBean;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class FirstBeanTest {
    public static void main(String[] args) {
        BeanFactory bf=new XmlBeanFactory(new ClassPathResource("spring/beanFactoryTest.xml"));
        MyTestBean bean= (MyTestBean) bf.getBean("myTestBean");
        System.out.println(bean.getTestStr());
    }
}