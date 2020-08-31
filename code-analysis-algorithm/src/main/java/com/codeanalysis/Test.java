package com.codeanalysis;


import com.codeanalysis.jdk.stream.Collectors;
import com.codeanalysis.jdk.stream.IntStream;

import java.util.List;

/**
 * @author Gavin
 * @date 2020/8/16
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> list = IntStream.range(0, 10).boxed().map(x -> x * 2).collect(Collectors.toList());
        System.out.println(list);
    }

}
