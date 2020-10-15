package com.codeanalysis.序01_LeetCode刷题班.第3课贪心算法;

import java.util.Arrays;

/**
 * @author Gavin
 * @date 2020/10/12 0:39
 *
 *
 */
public class 例4a跳跃游戏 {

    /**
     * 只是问能不能跳，没有问怎么跳，所以只要一格格遍历，并检查我走到该步时还能不能走下去即可。
     * 我走到该步时能不能走下去，就是要记录我以前的步骤中能让我到达的最远位置。
     *
     * @param arr
     * @return
     */

    public static boolean canJump(int[] arr) {
        if (arr == null || arr.length == 0) {
            return true;
        }
        int[] index = Arrays.copyOf(arr, arr.length);
        for (int i = 0; i < index.length; i++) {
            index[i] = i + index[i];
        }
        int jump = 0;
        int maxIndex = index[0];
        while (jump < index.length && jump <= maxIndex) {
            if (maxIndex < index[jump]) {
                maxIndex = index[jump];
            }
            jump++;
        }
        if (jump == index.length) {
            return true;
        }
        return false;
    }

    /**
     *
     * 思路：从数组的第一个位置开始，往后一格一格遍历数组，当所遍历的位置还没超出可reach范围时，
     * 根据跳力更新可reach范围，可遍历的范围必须小于等于reach值。
     * 若可reach范围可覆盖数组最后一个位置，则可到达；若不可覆盖则不可到达。
     *  public boolean jumpGame(int[] num){
     *         int N=num.length,reach=0;
     *         for(int i=0;i<=reach;i++){
     *             if(reach<i+num[i]) reach=i+num[i];
     *             if(reach>=N-1) return true;
     *         }
     *         return false;
     *     }
     *
     */

    public static void main(String[] args) {
        System.out.println(canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(canJump(new int[]{3, 2, 1, 0, 4}));
    }
}
