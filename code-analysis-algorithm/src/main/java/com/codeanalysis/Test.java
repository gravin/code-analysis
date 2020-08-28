package com.codeanalysis;

/**
 * @author Gavin
 * @date 2020/8/16
 */
public class Test {
    public static void main(String[] args) {
        hello1();
    }

    public static void hello1() {
        System.out.println("hello1");
        hello2();
    }

    public static void hello2() {
        System.out.println("hello2");
        hello3();
    }

    public static void hello3() {
        System.out.println("hello3");
        hello4();
    }

    public static void hello4() {
        System.out.println("hello4");
    }

}
