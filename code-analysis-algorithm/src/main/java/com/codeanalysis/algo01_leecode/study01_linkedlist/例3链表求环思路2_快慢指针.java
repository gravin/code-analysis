package com.codeanalysis.algo01_leecode.study01_linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Gavin
 * @date 2020/10/3
 *
 *
 * 假设到环切点距离为x, 相遇点距环切点在环中距离为y, 那为x+y必为环周长的整数倍
 * （因为快指针和慢指针相遇时共走过长度相差整数环周长，那么x-(R-y)也必为环周长的整数倍
 * 所以一指针从相遇点继续往前走，另一指针从链表头往前走，必然在圆切点处相遇
 *
 */
public class 例3链表求环思路2_快慢指针 {
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
        ListNode slow = head;
        ListNode fast = head;
        ListNode meet = null;
        while (fast != null) {
            if (fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                meet = slow;
                break;
            }
        }
        if (meet != null) {
            while (meet != null & head != null) {
                if (meet == head) {
                    return meet;
                }
                meet = meet.next;
                head = head.next;
            }
        }
        return null;
    }
}
