package com.codeanalysis.jdk.lambda;

/**
 * @author Gavin
 * @date 2020/9/6
 */
@FunctionalInterface
interface Print<T> {
    public void print(T x);
}

public class LambdaTest {
    //待测试的静态方法
    public static void printString(String s, Print<String> print) {
        print.print(s);
    }
    public static void main(String[] args) {
        printString("test", (x) -> System.out.println(x));
    }
}