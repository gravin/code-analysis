package com.codeanalysis;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Gavin
 * @date 2020/8/5
 */
public class TestHashMap {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        try {
            lock.lock();
            System.out.println(123);
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
//
//        Map<String,Integer> map = new HashMap<>();
//        map.put("A",1);
    }
}
