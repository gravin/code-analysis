package com.test.springboot;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.ClassUtils;

import java.util.List;

/**
 * @author Gavin
 * @date 2020/8/29
 */
public class TestSpringFactoriesLoader {
    public static void main(String[] args) {
        List<String> list = SpringFactoriesLoader.loadFactoryNames(ApplicationContextInitializer.class, ClassUtils.getDefaultClassLoader());
        System.out.println("SpringFactoriesLoader loading "+list);
    }
}
