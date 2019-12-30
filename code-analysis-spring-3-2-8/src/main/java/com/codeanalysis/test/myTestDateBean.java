package com.codeanalysis.test;

import com.codeanalysis.MyTestBean;
import com.codeanalysis.MyTestDateBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class myTestDateBean {
    public static void main(String[] args) {
//        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("spring/beanFactoryTest.xml"));
        ApplicationContext bf=new ClassPathXmlApplicationContext("spring/myTestDateBean.xml");
        MyTestDateBean bean= (MyTestDateBean) bf.getBean("myTestBean");
        bean.test();
    }
}