package com.codeanalysis;

/**
 * @author Gavin
 * @date 2020/8/16
 */
public class Test {
    public static void main(String[] args) {
        try {
            Thread.currentThread().interrupt();
        }catch (Throwable t){
            t.printStackTrace();
        }finally {
            if(Thread.currentThread().isInterrupted()){
                System.out.println("interrupted");
            }
        }
    }
}
