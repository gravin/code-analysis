package com.codeanalysis.test;

import com.codeanalysis.MyTestBean;
import com.codeanalysis.MyTestBean2;
import com.codeanalysis.MyTestBean2Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AopTest3JDKProxy {

    public static void main(String[] args) {
//        testJDKProxy();
		ApplicationContext bf = new ClassPathXmlApplicationContext("spring/aspectTest2.xml");
		MyTestBean2Service myTestBean2Service = (MyTestBean2Service) bf.getBean("test");
		myTestBean2Service.test();
    }

    public static void testJDKProxy() {

        MyTestBean2 myTestBean2 = new MyTestBean2();
        ThreadLocal threadLocal = new ThreadLocal();

        Object proxyInstance = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                MyTestBean2.class.getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        try {
                            System.out.println("开始执行啦");
                            if (proxy == this) {
                                System.out.println("invoke方法中的proxy参数即是this");
                            } else {
                                System.out.println("this对象为：" + System.identityHashCode(this));
                                System.out.println("proxy对象为：" + System.identityHashCode(proxy));
                            }
                            threadLocal.set(proxy);
                            return method.invoke(myTestBean2, args);
                        } finally {
                            System.out.println("执行结束");
                        }
                    }
                });

        MyTestBean2Service service = (MyTestBean2Service) proxyInstance;
        service.test();
        if (proxyInstance == threadLocal.get()) {
            System.out.println("invoke方法的proxy参数，即是proxyInstance本身");
        }

    }

}
