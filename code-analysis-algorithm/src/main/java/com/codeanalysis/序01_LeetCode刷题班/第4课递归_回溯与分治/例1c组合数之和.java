package com.codeanalysis.序01_LeetCode刷题班.第4课递归_回溯与分治;


import java.util.*;
import java.util.stream.Collectors;


/**
 * -ea -javaagent:D:\src\statistics\target\statistics-1.0-SNAPSHOT.jar=com.codeanalysis
 */
public class 例1c组合数之和 {
    public static void main(String[] args) {
        List<List<Integer>> result = new ArrayList<>();
        List<String> ret = new ArrayList<>();
        result.add(new ArrayList<>());
        List<Integer> item = new ArrayList<>();
        List<Integer> inputs = Arrays.asList(10, 1, 2, 7, 6, 1, 5);
        int target = 8;
        int sum = 0;
        Collections.sort(inputs);
        generate(0, inputs, item, result, ret, sum, target);
        System.out.println(result);
    }

    public static void generate(int i, List<Integer> nums, List<Integer> item, List<List<Integer>> result, List<String> ret, int sum, int target) {
        if (i >= nums.size() || sum >= target) {
            return;
        }
        sum += nums.get(i);
        item.add(nums.get(i));
        String str = item.stream().map(x -> String.valueOf(x)).collect(Collectors.joining("|::|", "", ""));
        if (!ret.contains(str) && Objects.equals(sum, target)) {
            List<Integer> list = new ArrayList<>();
            list.addAll(item);
            result.add(list);
            ret.add(str);
        }
        generate(i + 1, nums, item, result, ret, sum, target);
        sum -= nums.get(i);
        item.remove(item.size() - 1);
        generate(i + 1, nums, item, result, ret, sum, target);
    }

}
