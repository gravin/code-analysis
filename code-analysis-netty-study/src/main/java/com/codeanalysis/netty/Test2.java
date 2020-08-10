package com.codeanalysis.netty;

/**
 * @author Gavin
 * @date 2020/7/20
 */
public class Test2 {

    public static void main(String[] args) {
        Object o = new Object();
        Thread t1= new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o){
                    System.out.println(Thread.currentThread().getName()+" finished work");
                    o.notifyAll();
                    try {
                        Thread.sleep(50000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"worker 1");

        Thread t2= new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o){
                    try {
                        System.out.println(Thread.currentThread().getName()+" wait to work");
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" finished work");
                }
            }
        },"worker 2");

        t2.start();
        t1.start();
    }
}
