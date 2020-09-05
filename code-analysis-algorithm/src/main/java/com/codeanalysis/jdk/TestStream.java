package com.codeanalysis.jdk;


import com.codeanalysis.jdk.stream.Collectors;
import com.codeanalysis.jdk.stream.IntStream;
import com.codeanalysis.jdk.stream.Stream;

import java.util.Arrays;
import java.util.List;

/**
 * @Author xiongyx
 * on 2019/3/5.
 */
public class TestStream {

    public static void main(String[] args){
        // 生成关于list的流
        Stream<Integer> intStream = IntStream.range(1,6).boxed();
        // intStream基础上过滤出偶数的流
//        Stream<Integer> filterStream =  intStream.filter(item-> item%2 == 0);
        // filterStream基础上映射为平方的流
//        Stream<Integer> mapStream = filterStream.map(item-> item * item);
        // mapStream基础上截取前三个的流
        Stream<Integer> limitStream = intStream.limit(2);

        // 最终结果累加求和(初始值为0)
        Integer sum = limitStream.reduce(0,(i1,i2)-> i1+i2);

        System.out.println(sum); // 20
    }
}
