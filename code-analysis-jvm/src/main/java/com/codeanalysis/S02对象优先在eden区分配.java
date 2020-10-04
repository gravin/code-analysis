package com.codeanalysis;

/**
 * @author Gavin
 * @date 2020/9/24
 * 添加参数 -XX:+PrintGCDetails
 */
public class S02对象优先在eden区分配 {
    public static void main(String[] args) {
        byte[] allocation1, allocation2;
        allocation1 = new byte[30900*1024];
//        allocation2 = new byte[900*1024];
    }
}
