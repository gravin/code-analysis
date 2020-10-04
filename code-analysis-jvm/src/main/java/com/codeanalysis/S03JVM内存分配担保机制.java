package com.codeanalysis;

/**
 * @author Gavin
 * @date 2020/9/24
 * <p>
 * https://cloud.tencent.com/developer/article/1082730
 *
 * -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
 *
 * -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseParallelGC
 */
public class S03JVM内存分配担保机制 {
    private static final int _1mb = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1mb];
        allocation2 = new byte[2 * _1mb];
        allocation3 = new byte[2 * _1mb];
        allocation4 = new byte[4 * _1mb];

        synchronized (S03JVM内存分配担保机制.class){
            try {
                S03JVM内存分配担保机制.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
