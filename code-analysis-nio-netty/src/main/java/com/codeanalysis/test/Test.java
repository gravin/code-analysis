package com.codeanalysis.test;

public class Test {
    public static void main(String[] args) {
        test(ThreadNameDeterminer.CURRENT);
        String test = null;
        assert test == null;
    }

    public static void test(ThreadNameDeterminer threadNameDeterminer){
        System.out.println(123);
    }
}
