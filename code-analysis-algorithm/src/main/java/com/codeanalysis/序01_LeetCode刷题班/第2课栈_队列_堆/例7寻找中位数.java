package com.codeanalysis.序01_LeetCode刷题班.第2课栈_队列_堆;

import java.util.PriorityQueue;

/**
 * @author Gavin
 * @date 2020/10/11 17:06
 */
public class 例7寻找中位数 {
    static class MedianFinder {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1, o2) -> o1 - o2);

        MedianFinder addNum(int num) {
            if (maxHeap.size() == 0) {
                maxHeap.add(num);
            } else if (maxHeap.size() == minHeap.size()) {
                if (num <= minHeap.peek()) {
                    maxHeap.add(num);
                } else {
                    maxHeap.add(minHeap.remove());
                    minHeap.add(num);
                }
            } else if (maxHeap.size() > minHeap.size()) {
                if (num >= maxHeap.peek()) {
                    minHeap.add(num);
                } else {
                    minHeap.add(maxHeap.remove());
                    maxHeap.add(num);
                }
            }
            return this;
        }

        double findMedian() {
            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            } else {
                return (maxHeap.peek() + minHeap.peek()) / 2d;
            }
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1).addNum(2).addNum(3).addNum(4);
        System.out.println(medianFinder.findMedian());
    }
}
