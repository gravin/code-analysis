package com.codeanalysis.algo01_leecode.study01_linkedlist;

/**
 * @author Gavin
 * @date 2020/10/3
 */
public class 例4链表划分 {
    final static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(4);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(2);
        ListNode a5 = new ListNode(5);
        ListNode a6 = new ListNode(2);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;
        a5.next = a6;
        ListNode head = partition(a1, 3);
        printListNode(head);
    }

    private static ListNode partition(ListNode head, int i) {
        ListNode lessHead = new ListNode(0);
        ListNode moreHead = new ListNode(0);      // 把头节点置为0，免得了过多的非空判断
        ListNode lessTail = lessHead;
        ListNode moreTail = moreHead;
        while (head != null) {
            if (head.val < i) {
                lessTail.next = head;
                lessTail = head;
            } else {
                moreTail.next = head;
                moreTail = head;
            }
            head = head.next;
        }
        lessTail.next = moreHead.next;
        moreTail.next = null;
        return lessHead.next;
    }

    private static void printListNode(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            head = head.next;
        }
        System.out.println();
    }

//    private static ListNode partition(ListNode head, int i) {
//        ListNode small = null;
//        ListNode smallTail = null;
//        ListNode large = null;
//        ListNode largeTail = null;
//        while (head != null) {
//            ListNode next = head.next;
//            head.next = null;
//            if (head.val < i) {
//                if (small == null) {
//                    small = head;
//                    smallTail = head;
//                } else {
//                    smallTail.next = head;
//                    smallTail = head;
//                }
//            } else {
//                if (large == null) {
//                    large = head;
//                    largeTail = head;
//                } else {
//                    largeTail.next = head;
//                    largeTail = head;
//                }
//            }
//            head = next;
//        }
//        head = small;
//        if (head != null) {
//            if (large != null) {
//                smallTail.next = large;
//            }
//        } else {
//            head = large;
//        }
//        return head;
//    }
}
