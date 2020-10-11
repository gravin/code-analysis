package com.codeanalysis.序01_LeetCode刷题班.第1课链表;

/**
 * @author Gavin
 * @date 2020/10/3
 */
public class 例2求两个链表的交点 {

    static final class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode b1 = new ListNode(10);
        ListNode b2 = new ListNode(20);
        ListNode b3 = new ListNode(30);
        ListNode c1 = new ListNode(100);
        ListNode c2 = new ListNode(200);
        ListNode c3 = new ListNode(300);
        a1.next = a2;
        b1.next = b2;
        b2.next = b3;
        a2.next = c1;
        b3.next = c1;
        c1.next = c2;
        c2.next = c3;
        ListNode listNode = getIntersectNode(a1, c1);
        System.out.println(listNode.val);
    }

    private static ListNode getIntersectNode(ListNode h1, ListNode h2) {
        int len1 = getListLengh(h1);
        int len2 = getListLengh(h2);
        if (len1 > len2) {
            h1 = moveNPositions(h1, len1 - len2);
        } else if (len2 > len1) {
            h2 = moveNPositions(h2, len2 - len1);
        }
        while (h1 != null && h2 != null && h1 != h2) {
            h1 = h1.next;
            h2 = h2.next;
        }
        return h1;
    }

    private static ListNode moveNPositions(ListNode h1, int p) {
        while (p > 0) {
            h1 = h1.next;
            p--;
        }
        return h1;
    }

    private static int getListLengh(ListNode h) {
        int i = 0;
        while (h != null) {
            h = h.next;
            i++;
        }
        return i;
    }

}
