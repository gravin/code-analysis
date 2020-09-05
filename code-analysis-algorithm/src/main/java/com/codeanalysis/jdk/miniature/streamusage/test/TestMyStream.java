package com.codeanalysis.jdk.miniature.streamusage.test;

import com.codeanalysis.jdk.miniature.streamusage.stream.MyStream;
import com.codeanalysis.jdk.miniature.streamusage.stream.Stream;
import com.codeanalysis.jdk.miniature.streamusage.stream.genetator.CollectionStreamGenerator;
import com.codeanalysis.jdk.miniature.streamusage.stream.genetator.IntegerStreamGenerator;

/**
 * @Author xiongyx
 * @Date 2019/3/6
 */
public class TestMyStream {

    public static void main(String[] args){
        // 生成关于list的流
        Stream<Integer> intStream = IntegerStreamGenerator.getIntegerStream(1,10);
        // intStream基础上过滤出偶数的流
        Stream<Integer> filterStream =  intStream.filter(item-> item%2 == 0);
        // filterStream基础上映射为平方的流
        Stream<Integer> mapStream = filterStream.map(item-> item * item);
        // mapStream基础上截取前三个的流
        Stream<Integer> limitStream = mapStream.limit(2);

        // 最终结果累加求和(初始值为0)
        Integer sum = limitStream.reduce(0,(i1,i2)-> i1+i2);

        System.out.println(sum); // 20
    }

    private static boolean idOdd(int num){
        return (num % 2 != 0);
    }

    private static Integer square(int num){
        return num * num;
    }

    private static Integer scaleTwo(int num){
        return num * 2;
    }

    private static String toStr(int num){
        return num + "";
    }
}
