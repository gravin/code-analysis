package com.codeanalysis.algo01_leecode.study01_linkedlist;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author Gavin
 * @date 2020/10/4
 */
public class 例6b排序链表的合并_多个 {
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

        ListNode combined = combineSortedList(Arrays.asList(a1, b1, c1));
        printList(combined);
    }

    private static ListNode combineSortedList(List<ListNode> heads) {
        ListNode newHead = new ListNode(-999);
        ListNode newTail = newHead;
        Integer curr = null;
        while ((curr = getMin(heads)) != null) {
            newTail.next = heads.get(curr);
            newTail = newTail.next;
            heads.set(curr, heads.get(curr).next);
        }
        return newHead.next;
    }

    private static Integer getMin(List<ListNode> heads) {
        if (heads == null || heads.size() == 0) {
            return null;
        }
        Integer min = null;
        for (int i = 0; i < heads.size(); i++) {
            ListNode head = heads.get(i);
            if (head == null) {
                continue;
            }
            if (min == null || head.val < heads.get(min).val) {
                min = i;
            }
        }
        return min;
    }


    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
