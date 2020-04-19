package com.codeanalysis;

import org.amino.ds.lockfree.LockFreeList;
import org.amino.ds.lockfree.LockFreeVector;

import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CopyList {
    public static void test1(AccessListTread t, String name){
        CounterPoolExecutor exe0=new CounterPoolExecutor(2000,2000,0L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
        exe0.TASK_COUNT=8000;
        exe0.funcname=name;
        exe0.startTime=System.currentTimeMillis();
        for(int i=0;i<exe0.TASK_COUNT;i++)
            exe0.submit(t);//测试数据：8000
        exe0.shutdown();
    }
    public static void main(String[] args) {
        AccessListTread t=new AccessListTread();
        t.initCopyOnWriteArrayList();
        test1(t,"testCopyOnWriteArrayList");
        t.initVector();
        test1(t,"testVector");
        t.initLockFreeList();
        test1(t,"testLockFreeList");
        t.initLockFreeVector();
        test1(t,"testLockFreeVector");
    }

}
class CounterPoolExecutor extends ThreadPoolExecutor {
    public AtomicInteger count=new AtomicInteger(0);//统计次数
    public long startTime=0;
    public String funcname="";
    public int TASK_COUNT=0;
    public CounterPoolExecutor(int corePoolSize, int maximumPoolSize,
                               long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }
    @Override
    protected void afterExecute(Runnable r,Throwable t){
        int l=count.addAndGet(1);
        if(l==TASK_COUNT){
            System.out.println(funcname+"spend time:"+(System.currentTimeMillis()-startTime));
        }
    }
}



class AccessListTread implements Runnable{
    Random rand=new Random();
    List list;
    public AccessListTread() {
    }
    @Override
    public void run() {
        try {
            for(int i=0;i<1000;i++)
//				getList(rand.nextInt(1000));
                handleList(rand.nextInt(1000));
            Thread.sleep(rand.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Object getList(int nextInt) {
        return list.get(nextInt);
    }
    private Object handleList(int index) {
        list.add(index);
        list.remove(index%list.size());
        return null;
    }
    //test
    public void initCopyOnWriteArrayList(){
        list=new CopyOnWriteArrayList();
        for(int i=0;i<1000;i++)
            list.add(i);
    }
    public void initVector(){
        list=new Vector();
        for(int i=0;i<1000;i++)
            list.add(i);
    }
    public void initLockFreeList(){
        list=new LockFreeList();
        for(int i=0;i<1000;i++)
            list.add(i);
    }
    public void initLockFreeVector(){
        list=new LockFreeVector();
        for(int i=0;i<1000;i++)
            list.add(i);
    }
}
