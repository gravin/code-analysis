package com.test.springboot;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.ClassUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Gavin
 * @date 2020/8/29
 */
public class TestSpringFactoriesLoader {
    public static void main(String[] args) {
        List<String> list = SpringFactoriesLoader.loadFactoryNames(ApplicationContextInitializer.class, ClassUtils.getDefaultClassLoader());
        System.out.println("SpringFactoriesLoader loaded \n"+list.stream().collect(Collectors.joining("\n")));
    }
}
