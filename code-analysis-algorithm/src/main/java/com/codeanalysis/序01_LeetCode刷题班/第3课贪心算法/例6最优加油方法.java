package com.codeanalysis.序01_LeetCode刷题班.第3课贪心算法;

/**
 * @author Gavin
 * @date 2020/10/12 16:12
 */
public class 例6最优加油方法 {
    static class GasFill {
        int distance;
        int amount;

        public GasFill(int distance, int amount) {
            this.distance = distance;
            this.amount = amount;
        }
    }

    static int getMinimumStop(int L, int P, GasFill[] gasFills) {

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(getMinimumStop(25, 4,
                new GasFill[]{
                        new GasFill(4, 4),
                        new GasFill(4, 4),
                }));
    }
}
