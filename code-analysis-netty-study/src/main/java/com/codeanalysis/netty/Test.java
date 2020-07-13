package com.codeanalysis.netty;

/**
 * @author Gavin
 * @date 2020/7/7
 */
public class Test {
    private class InnerTest {
        public void hello() {
            System.out.println("aaaaaaaaaa");
        }
    }

    public void hello() {
        InnerTest innerTest = new InnerTest();
        innerTest.hello();
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.hello();
    }
}
