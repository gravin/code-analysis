package com.codeanalysis.algs4_study01;

import com.codeanalysis.algs4.Insertion;
import com.codeanalysis.algs4.Selection;

import java.util.Arrays;

/**
 * @author Gavin
 * @date 2020/10/5
 *
 *
 * https://blog.csdn.net/weixin_41190227/article/details/86600821
 *
 *
 * http://www.guqiankun.com/sortalgorithm/insertion-sort
 *
 * 快速排序算法（三种分区方法要熟练！）
 * https://www.cnblogs.com/Black-treex/p/12722606.html
 *
 */
public class 各种排序算法 {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 2, 5, 4};
        bubbleSort(Arrays.copyOf(arr, arr.length));
        selectSort(Arrays.copyOf(arr, arr.length));
        insertSort(Arrays.copyOf(arr, arr.length));
        shellSort(Arrays.copyOf(arr, arr.length));
        System.out.println(Arrays.toString(mergeSort(Arrays.copyOf(arr, arr.length))));


    }


    /**
     * 归并排序
     * <p>
     * 该算法是采用分治法（Divide and Conquer）的一个非常典型的应用
     *
     * @param array
     * @return
     */
    public static int[] mergeSort(int[] array) {
        if (array.length < 2) return array;
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    /**
     * 归并排序——将两段排序好的数组结合成一个排序数组
     *
     * @param left
     * @param right
     * @return
     */
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length)
                result[index] = right[j++];
            else if (j >= right.length)
                result[index] = left[i++];
            else if (left[i] > right[j])
                result[index] = right[j++];
            else
                result[index] = left[i++];
        }
        return result;
    }


    public static void shellSort(int[] arr) {
        //分组插入
        int len = arr.length;
        int temp, gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                temp = arr[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && arr[preIndex] > temp) {
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }
                arr[preIndex + gap] = temp;
            }
            gap /= 2;
        }
        System.out.println(Arrays.toString(arr));
    }

    private static void insertSort(int[] arr) {
        /**
         *
         * 从第一个元素开始，该元素可以认为已经被排序；
         * 取出下一个元素，在已经排序的元素序列中从后向前扫描；
         * 如果该元素（已排序）大于新元素，将该元素移到下一位置；
         * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
         * 将新元素插入到该位置后；
         * 重复步骤2~5。
         *
         * 假设前i-1个为已排序的，则插入第i个时，即把i与第i-1,i-2,i-3....比较
         * 如果i比第i-1个小，i-1后移一位，则此时空出i-1位
         * 再比较是否比第i-2个小，如果还是比i-2小，那么i-2后移一位，即会放在前面的i-1位上
         * 如此继续
         * 如果i-2大些，那么说明此时间空出的位置就是给第i个元素的，那么把第i个元素的值放在此即可
         */
        if (arr.length == 0)
            return;
        int current;
        for (int i = 0; i < arr.length - 1; i++) {
            current = arr[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < arr[preIndex]) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
        System.out.println(Arrays.toString(arr));
    }

    private static void selectSort(int[] arr) {
        /**
         *
         * 初始状态：无序区为R[1..n]，有序区为空；
         * 第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。该趟排序从当前无序区中-选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
         * n-1趟结束，数组有序化了。
         *
         * 10个位置，
         * 每次把第i位放上最小的，
         * 方法是是从后(10-i)个位置中找出最小的与第i位交换
         */

        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                int tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
            }
        }
        System.out.println(Arrays.toString(arr));
    }


    private static void bubbleSort(int[] arr) {
        /**
         *
         * 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
         * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
         * 针对所有的元素重复以上的步骤，除了最后一个；
         * 重复步骤1~3，直到排序完成。
         *
         * 每次比较把最大的下沉到最后一个元素
         *
         * 如果有10个元素，
         * 第一次将是遍历前9个元素，每个元素和后一元素比较，那么就是比较9次
         *
         */
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
