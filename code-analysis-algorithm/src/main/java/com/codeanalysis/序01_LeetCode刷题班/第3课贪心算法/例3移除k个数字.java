package com.codeanalysis.序01_LeetCode刷题班.第3课贪心算法;

import com.codeanalysis.algs4.Stack;

/**
 * @author Gavin
 * @date 2020/10/11 23:59
 */
public class 例3移除k个数字 {
    public static String removeKdigits(String num, int k) {
        Stack<Character> remain = new Stack<>();
        for (int i = 0; i < num.length(); i++) {
            if (i == 0) {
                remain.push(num.charAt(i));
            } else {
                while (!remain.isEmpty() && num.charAt(i) < remain.peek() && k > 0) {
                    remain.pop();
                    k--;
                }
                if (!remain.isEmpty() || num.charAt(i) != '0') {
                    remain.push(num.charAt(i));
                }
            }
        }
        while (k > 0) {
            remain.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder();
        while (!remain.isEmpty()) {
            sb.insert(0, remain.pop());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeKdigits("1001230", 3));
    }
}
