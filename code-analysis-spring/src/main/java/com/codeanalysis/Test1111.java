package com.codeanalysis;

import org.apache.commons.lang3.reflect.FieldUtils;

/**
 * @author Gavin
 * @date 2020/9/15
 */
public class Test1111 {
    public static final Integer a=0;
    public static void main(String[] args) {
        System.out.println(FieldUtils.getDeclaredField(Test1111.class,"a").getType().isPrimitive());
    }
}
