package com.codeanalysis.test;

public class ExtCls03 {
    public static Integer i=0;
    static {
        int i=10;
        System.out.println(i);
    }

    public ExtCls03() {
        long iname = 111;
        i=100;
        while(i>95){
            int j=3000;
            System.out.println(j);
            i--;
        }
        Long i2 = 200l;
        System.out.println(i2);
    }
}
