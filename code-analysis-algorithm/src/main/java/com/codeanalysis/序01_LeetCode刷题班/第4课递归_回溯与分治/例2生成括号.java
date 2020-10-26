package com.codeanalysis.序01_LeetCode刷题班.第4课递归_回溯与分治;

import java.util.ArrayList;
import java.util.List;

public class 例2生成括号 {
    public static void main(String[] args) {
        List<String> result = new ArrayList<>();
        generateParenthesis("", 3, 0, 0, result);
        System.out.println(result);
    }

    public static void generateParenthesis(String s, int n, int left, int right, List<String> result) {
        if (left == n && right == n) {
            result.add(s);
            return;
        }
        if (left < n) {
            generateParenthesis(s + "(", n, left + 1, right, result);
        }
        if (left > right) {
            generateParenthesis(s + ")", n, left, right + 1, result);
        }
    }
}
