package com.codeanalysis.sample.Introspector;

import com.codeanalysis.HelloWorldTest;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;

public class BeanInfoTest {

    public static void main(String[] args) throws IntrospectionException {
//        BeanInfo beanInfo = Introspector.getBeanInfo(Customer.class, Introspector.USE_ALL_BEANINFO);
        BeanInfo beanInfo2 = Introspector.getBeanInfo(Customer.class,Introspector.IGNORE_ALL_BEANINFO);
//        BeanInfo beanInfo3 = Introspector.getBeanInfo(Customer.class,Introspector.IGNORE_IMMEDIATE_BEANINFO);
        System.out.println();
    }
}
