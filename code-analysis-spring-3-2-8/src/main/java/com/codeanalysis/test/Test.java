package com.codeanalysis.test;

import com.codeanalysis.ApiControllerBeanManager;
import com.codeanalysis.HelloWorld;
import com.codeanalysis.HelloWorldTest;
import com.codeanalysis.TestController;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;

public class Test {

    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(HelloWorldTest.class, Introspector.USE_ALL_BEANINFO);
        BeanInfo beanInfo2 = Introspector.getBeanInfo(HelloWorldTest.class,Introspector.IGNORE_ALL_BEANINFO);
        BeanInfo beanInfo3 = Introspector.getBeanInfo(HelloWorldTest.class,Introspector.IGNORE_IMMEDIATE_BEANINFO);
        System.out.println();
//        try {
//            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
//            context.start();
//            System.out.println("启动成功");
//        } catch (Exception e) {
//            System.out.println("启动失败");
//            e.printStackTrace();
//        }
//        Object bean = ApiControllerBeanManager.getApiController("test","haha.haha","1.0");
//        ((TestController) bean).test();

//        synchronized (Test.class) {
//            try {
//                Test.class.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }
}
