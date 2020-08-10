package com.codeanalysis.netty.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Gavin
 * @date 2020/8/5
 */
public class Test {

    // 共享变量
    private static int count = 0;

    private static Lock lock = new ReentrantLock();

    // 操作共享变量的方法
    public static void incr(){
        // 为了演示效果  休眠一下子
        try {
            lock.lock();
            Thread.sleep(1);
            count ++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000 ; i++) {
            new Thread(()->Test.incr()).start();
        }

        Thread.sleep(4000);
        System.out.println("result:" + count);
    }

}

