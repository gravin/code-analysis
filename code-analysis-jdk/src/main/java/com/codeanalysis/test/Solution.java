package com.codeanalysis.test;

public class Solution {
    public static String findRecycleChars(String original) {
        if ((original == null) || original.length() == 0) {
            return null;
        }
        char[] oriArray = original.toCharArray();
        int first = 0;
        int end = 0;
        boolean[][] isPlalindrome = new boolean[oriArray.length][oriArray.length];
        for (int i = 0; i < oriArray.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (i - j < 2) {
                    isPlalindrome[i][j] = (oriArray[i] == oriArray[j]);
                } else {
                    isPlalindrome[i][j] = ((oriArray[i] == oriArray[j]) && isPlalindrome[i - 1][j + 1]);
                }
                if (isPlalindrome[i][j] && (i - j) > end - first) {
                    first = j;
                    end = i;
                }
            }
        }
        return String.valueOf(oriArray, first, end + 1);
    }

    public static void main(String[] args) {
        System.out.println(findRecycleChars("acbcadef"));
        System.out.println(findRecycleChars("abc"));
    }
}
