package com.codeanalysis.test;

import org.springframework.util.ClassUtils;
import org.springframework.util.MethodInvoker;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class Test33 {

    public static void main(String[] args) {
//
//        int typeDifferenceWeight = MethodInvoker.getTypeDifferenceWeight(new Class[]{int.class}, new Object[]{1});
//        int typeDifferenceWeight2 = MethodInvoker.getTypeDifferenceWeight(new Class[]{long.class}, new Object[]{1});
//        System.out.println(typeDifferenceWeight);
//        System.out.println(typeDifferenceWeight2);
//
//        System.out.println(int.class.isAssignableFrom(int.class));
//        System.out.println(int.class.isAssignableFrom(long.class));
//        System.out.println(int.class.isAssignableFrom(Integer.class));
//        System.out.println(Integer.class.isAssignableFrom(int.class));
//
//        System.out.println(ClassUtils.isAssignable(long.class,int.class));

//        Map<String, String> map = System.getenv();
//        for(Iterator<String> it = map.keySet().iterator(); it.hasNext();){
//            String key = it.next();
//            System.out.println(key + "=" + map.get(key));
//        }
//        System.out.println("---------------------------");
//        Properties properties = System.getProperties();
//        for(Iterator<Object> it = properties.keySet().iterator(); it.hasNext();){
//            Object key = it.next();
//            System.out.println(key + "=" + map.get(key));
//        }

        System.out.println(Number.class.isAssignableFrom(Integer.class));
        System.out.println(Integer.class.isAssignableFrom(Number.class));
    }
}
