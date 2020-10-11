package com.codeanalysis.序01_LeetCode刷题班.第2课栈_队列_堆;

import java.util.Stack;

/**
 * @author Gavin
 * @date 2020/10/10 11:24
 */
public class 例3包含min函数的栈 {
    static class MinStack {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();

        public void push(int x) {
            stack.push(x);
            if (minStack.isEmpty() || x < minStack.peek()) {
                minStack.push(x);
            } else {
                minStack.push(minStack.peek());
            }
        }

        public int pop() {
            minStack.pop();
            return stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        int getMin() {
            return minStack.peek();
        }
    }

    public static void main(String[] args) {
        MinStack stack=new MinStack();
        stack.push(1);
        stack.push(-1);
        stack.push(2);
        stack.push(-2);
        System.out.println(stack.getMin());
        System.out.println(stack.pop());
        System.out.println(stack.getMin());
    }
}
