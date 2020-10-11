package com.codeanalysis.序01_LeetCode刷题班.第2课栈_队列_堆;

import java.util.PriorityQueue;

/**
 * @author Gavin
 * @date 2020/10/11 15:38
 *
 * 求第k大的数，那么就是k-1个数比它大，
 *
 * 当数组长度就是k时，那么只要最小堆，堆顶值即为第k大
 * 当数组新增加时，只要看新加的数是否比堆顶大，如果比堆顶大，那么要移除堆顶，添加新数。新第k大的值在新堆的堆顶
 *
 * 即用最小堆保证我是目前序列中的第k大
 */
public class 例6数组中第k大的数 {
    public static void main(String[] args) {
        System.out.println(getKthLargest(new int[]{1, 2, 3, 4, 5}, 2));
        System.out.println(getKthLargest(new int[]{10, 2, 30, 4, 5}, 2));
    }

    public static int getKthLargest(int[] arr, int k) {
        if (k > arr.length) {
            throw new RuntimeException("error!");
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, ((o1, o2) -> o1 - o2));
        for (int i = 0; i < arr.length; i++) {
            if (i < k) {
                minHeap.add(arr[i]);
            } else if (minHeap.peek() < arr[i]) {
                minHeap.remove();
                minHeap.add(arr[i]);
            }
        }
        return minHeap.peek();
    }
}
