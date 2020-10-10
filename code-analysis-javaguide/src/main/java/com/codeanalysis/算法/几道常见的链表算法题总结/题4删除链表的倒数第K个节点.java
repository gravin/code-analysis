package com.codeanalysis.算法.几道常见的链表算法题总结;

/**
 * @author Gavin
 * @date 2020/10/10 0:21
 */
public class 题4删除链表的倒数第K个节点 {
    final static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {

        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        ListNode a5 = new ListNode(5);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;

        ListNode node = deleteKthToTailNode(a1, 5);
        printList(node);
    }

    private static void printList(ListNode ret) {
        while (ret != null) {
            System.out.print(ret.val);
            ret = ret.next;
        }
        System.out.println();
    }

    private static ListNode deleteKthToTailNode(ListNode head, int k) {
        ListNode p1 = head;
        ListNode p2 = head;
        int count = 0;
        while (p1 != null) {
            p1 = p1.next;
            if (count > k) {
                p2 = p2.next;
            }
            count++;
        }
        if (count < k) {

        } else if (count == k) {
            head = head.next;
        } else {
            p2.next = p2.next.next;
        }
        return head;
    }
}
