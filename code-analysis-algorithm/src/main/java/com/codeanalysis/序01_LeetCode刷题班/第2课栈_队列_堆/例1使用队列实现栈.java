package com.codeanalysis.序01_LeetCode刷题班.第2课栈_队列_堆;

/**
 * @author Gavin
 * @date 2020/10/10 11:10
 */
public class 例1使用队列实现栈 {
    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    static class MyStack {

        ListNode head = null;

        void push(int x) {
            ListNode node = new ListNode(x);
            node.next = head;
            head = node;
        }

        int pop() {
            if (head == null) {
                throw new RuntimeException("empty stack");
            }
            int x = head.val;
            head = head.next;
            return x;
        }

        int top() {
            if (head == null) {
                throw new RuntimeException("empty stack");
            }
            return head.val;
        }

        boolean empty() {
            return head == null;
        }

        public MyStack() {

        }
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.empty());
    }
}
