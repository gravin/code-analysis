package com.codeanalysis.序01_LeetCode刷题班.第2课栈_队列_堆;

import java.util.Stack;

/**
 * @author Gavin
 * @date 2020/10/10 14:42
 */
public class 例4合法的出栈序列 {
    public static void main(String[] args) {
        System.out.println(testValidStackPopSequence(new int[]{3, 2, 5, 4, 1}));
        System.out.println(testValidStackPopSequence(new int[]{3, 1, 2, 4, 5}));
    }

    private static boolean testValidStackPopSequence(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        Integer maxValue = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                for (int j = maxValue + 1; j <= arr[i]; j++) {
                    stack.push(j);
                }
                stack.pop();
                maxValue = arr[i];
            } else {
                if (stack.pop() != arr[i]) {
                    return false;
                }
            }
        }
        return maxValue == arr.length;
    }
}
