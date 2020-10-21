package com.codeanalysis.序01_LeetCode刷题班.第4课递归_回溯与分治;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Gavin
 * @date 2020/10/12 21:08
 */
public class 例1a求子集_按位算法 {
    public static void main(String[] args) {
        List<List<Integer>> subsets = subsets(Arrays.asList(1, 2, 3));
        System.out.println(subsets);
    }

    public static List<List<Integer>> subsets(List<Integer> nums) {
        List<List<Integer>> result = new ArrayList<>();
        Integer allSet = 1 << nums.size();
        for (int i = 0; i < allSet; i++) {
            List<Integer> item = new ArrayList<>();
            for (int j = 0; j < nums.size(); j++) {
                if ((i & (1 << j)) > 0) {
                    item.add(nums.get(j));
                }
            }
            result.add(item);
        }
        return result;
    }

}
