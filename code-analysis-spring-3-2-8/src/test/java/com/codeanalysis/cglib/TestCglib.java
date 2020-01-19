package com.codeanalysis.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.apache.tools.ant.util.ReflectUtil.invoke;
import static org.junit.Assert.assertEquals;

public class TestCglib {

    @Test
    public void testFixedValue() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "Hello fixed value cglib!";
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
//        assertEquals("Hello cglib!", proxy.test(null));
        Method test = SampleClass.class.getDeclaredMethod("test", String.class);
        test.setAccessible(true);
        Object ret = test.invoke(proxy, "");// 反射时字符串不能传null cglib 对私有及final均不代理
        System.out.println(ret);
    }

}
