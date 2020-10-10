package com.codeanalysis.算法.几道常见的链表算法题总结;

/**
 * @author Gavin
 * @date 2020/10/9 16:57
 */

/**
 * 剑指 offer:输入一个链表，反转链表后，输出链表的所有元素。
 */
public class 题2翻转链表 {

    final static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode a1 = new ListNode(2);
        ListNode a2 = new ListNode(4);
        ListNode a3 = new ListNode(3);
        a1.next = a2;
        a2.next = a3;

        ListNode reversed = reverseList(a1);
        printList(reversed);
    }

    private static void printList(ListNode ret) {
        while (ret != null) {
            System.out.print(ret.val);
            ret = ret.next;
        }
        System.out.println();
    }

    private static ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }
}
