package com.codeanalysis.序01_LeetCode刷题班.第1课链表;

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
        if (lists == null || lists.size() == 0) {
            return null;
        }
        if (lists.size() == 1) {
            return lists.get(0);
        }
        if (lists.size() == 2) {
            return mergeSortedList(lists.get(0), lists.get(1));
        }
        int mid = lists.size() / 2;
        ListNode sub1 = mergeSortedList(lists.subList(0, mid));
        ListNode sub2 = mergeSortedList(lists.subList(mid, lists.size()));
        return mergeSortedList(sub1, sub2);
    }

    private static ListNode mergeSortedList(ListNode h1, ListNode h2) {
        ListNode newHead = new ListNode(-999);
        ListNode newTail = newHead;

        while (h1 != null && h2 != null) {
            if (h1.val <= h2.val) {
                newTail.next = h1;
                h1 = h1.next;
            } else {
                newTail.next = h2;
                h2 = h2.next;
            }
            newTail = newTail.next;
        }
        if (h1 != null) {
            while (h1 != null) {
                newTail.next = h1;
                newTail = newTail.next;

                h1 = h1.next;
            }
        }
        if (h2 != null) {
            while (h2 != null) {
                newTail.next = h2;
                newTail = newTail.next;
                h2 = h2.next;
            }
        }
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
