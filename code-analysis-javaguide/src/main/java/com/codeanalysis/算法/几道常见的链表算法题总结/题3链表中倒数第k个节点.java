package com.codeanalysis.算法.几道常见的链表算法题总结;

/**
 * @author Gavin
 * @date 2020/10/9 17:03
 * <p>
 * <p>
 * <p>
 * <p>
 * 题目描述
 * 剑指offer: 输入一个链表，输出该链表中倒数第k个结点。
 * <p>
 * 问题分析
 * 链表中倒数第k个节点也就是正数第(L-K+1)个节点，知道了只一点，这一题基本就没问题！
 */
public class 题3链表中倒数第k个节点 {

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

        ListNode node = findKthToTailNode(a1, 5);
        System.out.println(node == null ? "nil" : node.val);
    }

    private static ListNode findKthToTailNode(ListNode head, int k) {
        ListNode p1 = head;
        ListNode p2 = head;
        int i = 0;
        while (p1 != null) {
            p1 = p1.next;
            if (i >= k) {
                p2 = p2.next;
            }
            i++;
        }
        if (i < k) {
            return null;
        }
        return p2;
    }
}
