package com.codeanalysis;

public class MyTestBean2 implements MyTestBean2Service {
    private String testStr="testStr";

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    public void test(){
        try {
            System.out.println("test");
        }catch (Throwable t){

        }
    }
}