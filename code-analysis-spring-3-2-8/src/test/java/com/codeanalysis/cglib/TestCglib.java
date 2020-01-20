package com.codeanalysis.cglib;

import net.sf.cglib.beans.*;
import net.sf.cglib.proxy.*;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestCglib {
// https://www.jianshu.com/p/cdbdf3da2b7e
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
        assertEquals("Hello cglib!", proxy.test(null));
//        Method test = SampleClass.class.getDeclaredMethod("test", String.class);
//        test.setAccessible(true);
//        Object ret = test.invoke(proxy, "");// 反射时字符串不能传null cglib 对私有及final均不代理
//        System.out.println(ret);
    }

    @Test
    public void testInvocationHandler() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                if (method.getDeclaringClass() != Object.class
                        && method.getReturnType() == String.class) {
//                    method.invoke(proxy, args); 此处若调用会无限循环，因为调用会给InvocationHandler拦截，然后又调用，又拦截。。。
                    return "Hello cglib!";
                } else {
                    throw new RuntimeException("Do not know what to do.");
                }
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        assertEquals("Hello cglib!", proxy.test(null));
//        assertNotEquals("Hello cglib!", proxy.toString());
    }

    @Test
    public void testMethodInterceptor() throws Exception {
        /**MethodInterceptor的创建和链入需要生成额外的字节码(类)(byte code)，
         * 同时会创建一些InvocationHandler不会创建的运行时对象(与InvocationHandler对比，性能更差)。
         * 由于此种原因，cglib还提供了另外的一些callback实现：
         */
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)// 原始对象，方法，参数，工具类
                    throws Throwable {
                if(method.getDeclaringClass() != Object.class
                        && method.getReturnType() == String.class) {
                    return "Hello cglib!";
                } else {
                    proxy.invokeSuper(obj, args);
                }
                return null;
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        assertEquals("Hello cglib!", proxy.test(null));
        assertNotEquals("Hello cglib!", proxy.toString());
        proxy.hashCode();// Does not throw an exception or result in an endless loop.
    }

    @Test
    public void testCallbackFilter() throws Exception {
        Enhancer enhancer = new Enhancer();
        CallbackHelper callbackHelper = new CallbackHelper(SampleClass.class, new Class[0]) {
            @Override
            protected Object getCallback(Method method) {
                if(method.getDeclaringClass() != Object.class
                        && method.getReturnType() == String.class) {
                    return new FixedValue() {
                        @Override
                        public Object loadObject() throws Exception {
                            return "Hello cglib!";
                        };
                    };
                } else {
                    return NoOp.INSTANCE; // A singleton provided by NoOp.
                }
            }
        };
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallbackFilter(callbackHelper);
        enhancer.setCallbacks(callbackHelper.getCallbacks());
        SampleClass proxy = (SampleClass) enhancer.create();
        assertEquals("Hello cglib!", proxy.test(null));
        assertNotEquals("Hello cglib!", proxy.toString());
        proxy.hashCode(); // Does not throw an exception or result in an endless loop.
    }

    @Test(expected = IllegalStateException.class)
    public void testImmutableBean() throws Exception {
        SampleBean bean = new SampleBean();
        bean.setValue("Hello world!");
        SampleBean immutableBean = (SampleBean) ImmutableBean.create(bean);
        assertEquals("Hello world!", immutableBean.getValue());
        bean.setValue("Hello world, again!");
        assertEquals("Hello world, again!", immutableBean.getValue());
        immutableBean.setValue("Hello cglib!"); // Causes exception.
    }

    @Test
    public void testBeanGenerator() throws Exception {
        BeanGenerator beanGenerator = new BeanGenerator();
        beanGenerator.addProperty("value", String.class);
        Object myBean = beanGenerator.create();

        Method setter = myBean.getClass().getMethod("setValue", String.class);
        setter.invoke(myBean, "Hello cglib!");
        Method getter = myBean.getClass().getMethod("getValue");
        assertEquals("Hello cglib!", getter.invoke(myBean));
    }

    @Test
    public void testBeanCopier() throws Exception {
        BeanCopier copier = BeanCopier.create(SampleBean.class, OtherSampleBean.class, false);
        SampleBean bean = new SampleBean();
        bean.setValue("Hello cglib!");
        OtherSampleBean otherBean = new OtherSampleBean();
        copier.copy(bean, otherBean, null);
        assertEquals("Hello cglib!", otherBean.getValue());
    }

    @Test
    public void bulkBeanTest() {
        BulkBean bulkBean = BulkBean.create(MultiFieldBean.class,
                new String[]{"getName", "getAge", "getAddress"},
                new String[]{"setName", "setAge", "setAddress"},
                new Class[]{String.class, Integer.class, String.class}
        );
        MultiFieldBean bean = new MultiFieldBean("mallen", "chengdu", 18);
        Object[] values = bulkBean.getPropertyValues(bean);
        assertEquals("mallen", values[0]);
        assertEquals(18, values[1]);
        assertEquals("chengdu", values[2]);
        // 设置属性
        bulkBean.setPropertyValues(bean, new Object[]{"mallen1", 19, "chengdu1"});
        values = bulkBean.getPropertyValues(bean);
        assertEquals("mallen1", values[0]);
        assertEquals(19, values[1]);
        assertEquals("chengdu1", values[2]);
    }

    @Test
    public void testBeanMap() throws Exception {
        SampleBean bean = new SampleBean();
        BeanMap map = BeanMap.create(bean);
        bean.setValue("Hello cglib!");
        assertEquals("Hello cglib", map.get("value"));
    }
}
