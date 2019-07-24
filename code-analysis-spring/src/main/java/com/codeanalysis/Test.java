package com.codeanalysis;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
            context.start();
            System.out.println("启动成功");
        } catch (Exception e) {
            System.out.println("启动失败");
            e.printStackTrace();
        }
        Object bean = ApiControllerBeanManager.getApiController("test","haha.haha","1.0");
        ((TestController) bean).test();

        synchronized (Test.class) {
            try {
                Test.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
