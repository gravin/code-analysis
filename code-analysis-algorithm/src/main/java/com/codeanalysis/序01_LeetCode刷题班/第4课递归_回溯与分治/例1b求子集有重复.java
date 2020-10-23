package com.codeanalysis.序01_LeetCode刷题班.第4课递归_回溯与分治;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * -ea -javaagent:D:\src\statistics\target\statistics-1.0-SNAPSHOT.jar=com.codeanalysis
 */
public class 例1b求子集有重复 {
    public static void main(String[] args) {
        List<List<Integer>> result = new ArrayList<>();
        List<String> ret = new ArrayList<>();
        result.add(new ArrayList<>());
        List<Integer> item = new ArrayList<>();
        List<Integer> inputs = Arrays.asList(2, 1, 2, 2);
        Collections.sort(inputs);
        generate(0, inputs, item, result, ret);
        System.out.println(result);
    }

    public static void generate(int i, List<Integer> nums, List<Integer> item, List<List<Integer>> result, List<String> ret) {
        if (i >= nums.size()) {
            return;
        }
        item.add(nums.get(i));
        String str = item.stream().map(x -> String.valueOf(x)).collect(Collectors.joining("|::|", "", ""));
        if (!ret.contains(str)) {
            List<Integer> list = new ArrayList<>();
            list.addAll(item);
            result.add(list);
            ret.add(str);
        }
        generate(i + 1, nums, item, result, ret);
        item.remove(item.size() - 1);
        generate(i + 1, nums, item, result, ret);
    }

}
