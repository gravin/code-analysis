package com.codeanalysis.algo01_leecode.study01_linkedlist;

import java.util.Arrays;
import java.util.List;

/**
 * @author Gavin
 * @date 2020/10/4
 */
public class 例6b排序链表的合并_多个_分治 {
    static final class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(4);
        ListNode a3 = new ListNode(6);
        a1.next = a2;
        a2.next = a3;

        ListNode b1 = new ListNode(0);
        ListNode b2 = new ListNode(5);
        ListNode b3 = new ListNode(7);
        b1.next = b2;
        b2.next = b3;

        ListNode c1 = new ListNode(1);
        ListNode c2 = new ListNode(3);
        c1.next = c2;

        ListNode merged = mergeSortedList(Arrays.asList(a1, b1, c1));
        printList(merged);
    }

    private static ListNode mergeSortedList(List<ListNode> lists) {
        ListNode newHead = new ListNode(-999);
        ListNode newTail = newHead;

        return newHead.next;
    }



    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
