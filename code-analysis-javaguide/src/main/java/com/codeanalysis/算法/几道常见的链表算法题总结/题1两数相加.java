package com.codeanalysis.算法.几道常见的链表算法题总结;

/**
 * @author Gavin
 * @date 2020/10/9 16:30
 */

/**
 * 题目描述
 * Leetcode:给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class 题1两数相加 {
    static final class ListNode {
        int value;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        ListNode a1 = new ListNode(2);
        ListNode a2 = new ListNode(4);
        ListNode a3 = new ListNode(3);
        a1.next = a2;
        a2.next = a3;

        ListNode b1 = new ListNode(5);
        ListNode b2 = new ListNode(6);
        ListNode b3 = new ListNode(4);
        b1.next = b2;
        b2.next = b3;

        ListNode ret = addList(a1, b1);
        printList(ret);
    }

    private static void printList(ListNode ret) {
        while (ret != null) {
            System.out.print(ret.value);
            ret = ret.next;
        }
        System.out.println();
    }

    private static ListNode addList(ListNode h1, ListNode h2) {
        ListNode newHead = new ListNode(-1);
        ListNode newTail = newHead;
        int tmp = 0;
        while (true) {
            if (h1 == null && h2 == null && tmp == 0) {
                break;
            }
            if (h1 != null) {
                tmp += h1.value;
                h1 = h1.next;
            }
            if (h2 != null) {
                tmp += h2.value;
                h2 = h2.next;
            }
            newTail.next = new ListNode(tmp % 10);
            newTail = newTail.next;
            tmp = tmp / 10;
        }

        return newHead.next;
    }


}
