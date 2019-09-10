package com.codeanalysis.test;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibTest {

    public static class HelloService {
        public void sayHello(String something) {
            System.out.println("hello, " + something);
        }
    }

    public static class HelloCGLibProxy implements MethodInterceptor {

        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("-----------"+method.getName()+"-------");
            return methodProxy.invokeSuper(o, objects);
        }
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloService.class);
        enhancer.setCallback(new HelloCGLibProxy());

        Object obj = enhancer.create();
        HelloService helloService = (HelloService) obj;
        helloService.sayHello("ok");
    }
}
