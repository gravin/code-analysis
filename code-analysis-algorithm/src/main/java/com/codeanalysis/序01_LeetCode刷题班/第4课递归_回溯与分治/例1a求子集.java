package com.codeanalysis.序01_LeetCode刷题班.第4课递归_回溯与分治;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Gavin
 * @date 2020/10/12 21:08
 */
public class 例1a求子集 {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        List<Integer> item = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        generate(0, nums, item, result);
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.print(String.format("[%d]", result.get(i).get(j)));
            }
            System.out.println();
        }

    }

    private static void generate(int i, List<Integer> nums, List<Integer> item, List<List<Integer>> result) {
        if (i >= nums.size()) {
            return;
        }

        item.add(nums.get(i));
        List<Integer> list = new ArrayList<>();
        list.addAll(item);
        result.add(list);
        generate(i + 1, nums, item, result);
        item.remove(item.size() - 1);
        generate(i + 1, nums, item, result);
    }
}
