package com.codeanalysis;

public class TestB {
    private TestA testA;

    public void b() {
        testA.a();
    }

    public TestA getTestA() {
        return testA;
    }

    public void setTestA(TestA testA) {
        this.testA = testA;
    }
}
