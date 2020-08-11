package com.codeanalysis;


import com.codeanalysis.jdk.locks.ReentrantLock;

/**
 * @author Gavin
 * @date 2020/8/5
 * -ea -javaagent:E:\src\statistics\target\statistics-1.0-SNAPSHOT.jar=com.codeanalysis
 */
public class TestAQS {

    public static int count = 0;
    public static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    int local = count;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count = local + 1;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread t1 = new Thread(runnable,"t1");
        Thread t2 = new Thread(runnable,"t2");
        Thread t3 = new Thread(runnable,"t3");
        t1.start();
        Thread.sleep(10);
        t2.start();
        Thread.sleep(10);
        t3.start();
        Thread.sleep(2000);
        System.out.println("total count " + count);
    }
}
