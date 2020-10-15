package com.codeanalysis.序01_LeetCode刷题班.第3课贪心算法;

/**
 * @author Gavin
 * @date 2020/10/12 9:15
 * <p>
 * <p>
 * <p>
 * https://blog.csdn.net/Shawn_Chan/article/details/80999573
 *
 * 相当于区域函数，一个区域映射到另一个区域，用这些区域把这些覆盖起来
 */
public class 例4b跳跃游戏2 {
    public static int jump(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int reach = arr[0];
        int nextReach = arr[0];
        int jumps = 1;
        for (int i = 1; i < arr.length; i++) {
            if (i > reach) {
                reach = nextReach;
                jumps++;
            }
            if (i + arr[i] > nextReach) {
                nextReach = i + arr[i];
            }
        }
        return jumps;
    }

    public static void main(String[] args) {
        System.out.println(jump(new int[]{2, 3, 1, 1, 4}));
    }
}
