package com.codeanalysis.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) {
        //代理类class文件存入本地磁盘
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "E:\\software\\JavaDecompiler");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);

        // 使用过滤去确定使用哪一个回调方法
/*        enhancer.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
                return 0;
            }
        });
        enhancer.setCallbacks(new Callback[]{
                new CglibProxyIntercepter()
        });*/

        enhancer.setCallback(new CglibProxyIntercepter());
        PersonService proxy = (PersonService) enhancer.create();
        proxy.setPerson();
        proxy.getPerson("1");
    }
}
