package com.codeanalysis.test;

import com.codeanalysis.TestA;
import com.codeanalysis.TestAA;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.config.AopConfigUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class circularReferenceWithAOPTest {
    public static void main(String[] args) {
//        ApplicationContext bf = new ClassPathXmlApplicationContext("spring/circularReferenceTestWithAOP.xml");
        BeanFactory bf=new XmlBeanFactory(new ClassPathResource("spring/circularReferenceTestWithAOP.xml"));
        ((XmlBeanFactory) bf).addBeanPostProcessor(bf.getBean(AopConfigUtils.AUTO_PROXY_CREATOR_BEAN_NAME, BeanPostProcessor.class));
        TestAA testAA = (TestAA) bf.getBean("testAA");
        testAA.aa();
    }
}
