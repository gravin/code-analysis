package com.codeanalysis;

public class TestAA {
    private TestBB testBB;

    public void aa() {
        System.out.println("calling testBB");
        testBB.bb();
    }

    public TestBB getTestBB() {
        return testBB;
    }

    public void setTestBB(TestBB testBB) {
        this.testBB = testBB;
    }
}
