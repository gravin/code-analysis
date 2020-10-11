package com.codeanalysis.序01_LeetCode刷题班.第3课贪心算法;

import java.util.List;

/**
 * @author Gavin
 * @date 2020/10/11 17:38
 */
public class 例2摇摆序列 {
    public static final int BEGIN = 0;
    public static final int UP = 1;
    public static final int DOWN = 2;

    public static int wiggleMaxLength(int[] arr) {
        if (arr == null) {
            return 0;
        }
        if (arr.length < 2) {
            return arr.length;
        }
        int state = BEGIN;
        int maxLength = 1;
        for (int i = 1; i < arr.length; i++) {
            switch (state) {
                case BEGIN:
                    if (arr[i] > arr[i - 1]) {
                        state = UP;
                        maxLength++;
                    } else if (arr[i] < arr[i - 1]) {
                        state = DOWN;
                        maxLength++;
                    }
                    break;
                case UP:
                    if (arr[i] < arr[i - 1]) {
                        state = DOWN;
                        maxLength++;
                    }
                    break;
                case DOWN:
                    if (arr[i] > arr[i - 1]) {
                        state = UP;
                        maxLength++;
                    }
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(wiggleMaxLength(new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8}));
    }
}
