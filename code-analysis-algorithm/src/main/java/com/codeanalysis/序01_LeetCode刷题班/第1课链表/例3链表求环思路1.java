package com.codeanalysis.序01_LeetCode刷题班.第1课链表;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Gavin
 * @date 2020/10/3
 */
public class 例3链表求环思路1 {
    final static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        ListNode a5 = new ListNode(5);
        ListNode a6 = new ListNode(6);
        ListNode a7 = new ListNode(7);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;
        a5.next = a6;
        a6.next = a7;
        a7.next = a3;
        ListNode cycle = detectCycle(a1);
        System.out.println(cycle.val);
    }

    private static ListNode detectCycle(ListNode head) {
        Set<ListNode> listNodeSet = new HashSet<>();
        while (head != null) {
            if (listNodeSet.contains(head)) {
                return head;
            }
            listNodeSet.add(head);
            head = head.next;
        }
        return null;
    }
}
