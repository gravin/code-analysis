package com.codeanalysis;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 * <p>
 * <p>
 * <p>
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 * <p>
 * For example,
 * Given height = [2,1,5,6,2,3],
 * return 10.
 */
public class SolutionLargestRectangleInHistogram {

    public int largestRectangleArea2(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();
        int i = 0;
        int maxArea = 0;
        int[] h = new int[height.length + 1];
        h = Arrays.copyOf(height, height.length + 1);
        String s = Arrays.stream(h).mapToObj(e -> String.valueOf(e)).collect(Collectors.joining(","));
        System.out.println("柱图" + s);
        while (i < h.length) {
            System.out.println(StringUtils.repeat("-",200));
            System.out.print("当前栈"+stack.toString()+",位置"+i);
            if (stack.isEmpty() || h[stack.peek()] <= h[i]) {
                if (stack.isEmpty()) {
                    System.out.print(";满足条件stack.isEmpty");
                } else if (h[stack.peek()] <= h[i]) {
                    System.out.print(";满足条件h[" + stack.peek() + "]<=h[" + i + "]," + h[stack.peek()] + "<=" + h[i]);
                }
                System.out.print(";入栈:[序号" + i + ",高度" + h[i] + "]");
                System.out.println();
                stack.push(i++);
            } else {
                if (!(h[stack.peek()] <= h[i])) {
                    System.out.print(";不满足条件h[" + stack.peek() + "]<=h[" + i + "]," + h[stack.peek()] + "<=" + h[i]);
                }
                int t = stack.pop();
                System.out.print(";出栈:[序号" + t + "，高度" + h[t] + "]");
                System.out.print(";计算宽度:" + (stack.isEmpty() ? i : i - stack.peek() - 1));
                System.out.print(";当前maxArea：" + maxArea);
                maxArea = Math.max(maxArea, h[t] * (stack.isEmpty() ? i : i - stack.peek() - 1));
                System.out.print(";计算maxArea：" + maxArea);
                System.out.println();
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] rectangles = new int[]{2, 1, 3, 6, 5, 5};
        System.out.println(new SolutionLargestRectangleInHistogram().largestRectangleArea2(rectangles));
    }
}
