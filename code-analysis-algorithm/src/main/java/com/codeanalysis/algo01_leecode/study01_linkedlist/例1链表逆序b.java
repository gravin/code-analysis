package com.codeanalysis.algo01_leecode.study01_linkedlist;

/**
 * @author Gavin
 * @date 2020/9/30
 */
public class 例1链表逆序b {
    final static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    private static ListNode reverseList(ListNode head, int m, int n) {
        ListNode newHead = null;
        ListNode reverseStart = null;
        ListNode newTail = null;
        int i = 1;
        while (head != null) {
            ListNode next = head.next;
            head.next = null;

            if (i < m) {
                if (newHead == null) {
                    newHead = head;
                } else {
                    newHead.next = head;
                }
                reverseStart = head;
                newTail = head;
            } else if (i >= m && i <= n) {
                if (reverseStart == null) {
                    if (newHead == null) {
                        newHead = head;
                        newTail = head;
                    } else {
                        head.next = newHead;
                        newHead = head;
                    }
                } else {
                    if (reverseStart.next == null) {
                        newTail = head;
                    }
                    head.next = reverseStart.next;
                    reverseStart.next = head;
                }
            } else {
                newTail.next = head;
                newTail = head;
            }
            head = next;
            i++;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        printList(node1);
        node1 = reverseList(node1, 2, 3);
        printList(node1);
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            head = head.next;
        }
        System.out.println();
    }
}
