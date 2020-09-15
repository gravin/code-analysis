package com.codeanalysis.test;


import com.codeanalysis.cscan.CscanTestBean;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

//-ea -javaagent:E:\src\statistics\target\statistics-1.0-SNAPSHOT.jar=org.springframework,com.codeanalysis
public class ComponentScanBeanTest {
    public static void main(String[] args) {
        ApplicationContext bf= new ClassPathXmlApplicationContext("spring/componentScanTest.xml");
//        XmlBeanFactory bf = new XmlBeanFactory(new ClassPathResource("spring/componentScanTest.xml"));
//        bf.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
        CscanTestBean bean= (CscanTestBean) bf.getBean("cscanTestBean");
        System.out.println(bean.getTestStr());
    }
}