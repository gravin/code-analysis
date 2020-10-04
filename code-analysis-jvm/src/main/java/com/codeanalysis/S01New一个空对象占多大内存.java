package com.codeanalysis;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author Gavin
 * @date 2020/9/24
 */

public class S01New一个空对象占多大内存 {
    public static class TObject {
        Byte b = new Byte((byte) 1);
        int i=0;
        int j;
        boolean x=false;
    }

    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(new TObject()).toPrintable());
        synchronized (S01New一个空对象占多大内存.class){
            try {
                S01New一个空对象占多大内存.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
