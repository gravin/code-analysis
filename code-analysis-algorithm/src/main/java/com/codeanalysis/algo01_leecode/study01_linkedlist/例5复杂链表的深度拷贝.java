package com.codeanalysis.algo01_leecode.study01_linkedlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Gavin
 * @date 2020/10/3
 */
public class 例5复杂链表的深度拷贝 {
    static final class ListNode {
        int val;
        ListNode next;
        ListNode random;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {

        ListNode a = new ListNode(5);
        ListNode b = new ListNode(3);
        ListNode c = new ListNode(6);
        a.next = b;
        b.next = c;
        a.random = c;
        b.random = a;
        c.random = c;
        printRandomListNode(a);
        ListNode aa = copyRandomListNode(a);
        System.out.println("after copy:");
        printRandomListNode(aa);
    }

    private static ListNode copyRandomListNode(ListNode head) {
        ListNode oldHead = head;
        Map<ListNode, Integer> nodeMap = new HashMap<>();
        ListNode newHead = null;
        ListNode newTail = null;
        List<ListNode> newList = new ArrayList<>();
        int i = -1;
        while (head != null) {
            nodeMap.put(head, ++i);
            if (newHead == null) {
                newHead = newTail = new ListNode(head.val);
            } else {
                newTail.next = new ListNode(head.val);
                newTail = newTail.next;
            }
            newList.add(newTail);
            head = head.next;
        }

        while (oldHead != null) {
            Integer idx = nodeMap.get(oldHead);
            Integer randomIdx = nodeMap.get(oldHead.random);
            ListNode newNode = newList.get(idx);
            ListNode newRandom = newList.get(randomIdx);
            if (newNode != null) {
                newNode.random = newRandom;
            }
            oldHead = oldHead.next;
        }
        return newHead;
    }

    private static void printRandomListNode(ListNode head) {
        ListNode oldHead = head;
        Map<ListNode, Integer> nodeMap = new HashMap<>();
        int i = -1;
        while (oldHead != null) {
            nodeMap.put(oldHead, ++i);
            oldHead = oldHead.next;
        }
        while (head != null) {
            System.out.println("node val:" + head.val + ",next:" + nodeMap.get(head.next) + ",random:" + nodeMap.get(head.random));
            head = head.next;
        }
    }

}
