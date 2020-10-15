package com.codeanalysis.序01_LeetCode刷题班.第3课贪心算法;

import java.util.Arrays;

/**
 * @author Gavin
 * @date 2020/10/12 14:07
 */
public class 例5射击汽球 {
    static class Ballon {
        int x1;
        int x2;

        public Ballon(int x1, int x2) {
            this.x1 = x1;
            this.x2 = x2;
        }

        @Override
        public String toString() {
            return "Ballon{" +
                    "x1=" + x1 +
                    ", x2=" + x2 +
                    '}';
        }
    }

    /**
     * 区间左端点排序之后，从第一个汽球区间到最后一个汽球区间，
     *
     * 不断在合并中缩小可射击区间，如果新的汽球不能加入新的可射击区间，则要新增射手。
     *
     * @param ballons
     * @return
     */
    public static int find(Ballon[] ballons) {
        Arrays.sort(ballons, (b1, b2) -> b1.x1 - b2.x1);
        int shootNum = 1;
        int shootBegin = ballons[0].x1;
        int shootEnd = ballons[0].x2;
        for (int i = 1; i < ballons.length; i++) {
            if (ballons[i].x1 <= shootEnd) {
                shootBegin = ballons[i].x1;
                if (ballons[i].x2 < shootEnd) {
                    shootEnd = ballons[i].x2;
                }
            } else {
                shootNum++;
                shootBegin=ballons[i].x1;
                shootEnd=ballons[i].x2;
            }
        }
        return shootNum;
    }

    public static void main(String[] args) {
        System.out.println(find(new Ballon[]{
                new Ballon(10, 16),
                new Ballon(2, 8),
                new Ballon(1, 6),
                new Ballon(7, 12),
        }));
    }
}
