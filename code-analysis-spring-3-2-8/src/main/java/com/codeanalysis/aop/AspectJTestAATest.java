package com.codeanalysis.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectJTestAATest {

    @Pointcut("execution(* com.codeanalysis.TestAA.aa(..))")
    public void test(){

    }

    @Before("test()")
    public void beforeTest(){
        System.out.println("beforeAA");
    }

//    @After("test()")
//    public void afterTest(){
//        System.out.println("afterTest");
//    }
//
//    @Around("test()")
//    public Object aroundTest(ProceedingJoinPoint p){
//        System.out.println("before1");
//        Object o = null;
//        try{
//            o=p.proceed();
//        }catch (Throwable e){
//            e.printStackTrace();
//        }
//        System.out.println("after1");
//        return o;
//    }
}
