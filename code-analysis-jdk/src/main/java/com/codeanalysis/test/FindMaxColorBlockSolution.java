package com.codeanalysis.test;

public class FindMaxColorBlockSolution {

    public int findMaxColorBlock(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        int maxColorBlock = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxColorBlock = Math.max(maxColorBlock, dfs(grid, i, j, grid[i][j]));
            }
        }
        return maxColorBlock;
    }

    public int dfs(int[][] grid, int r, int c, int color) {
        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
        // 如果已经计算过不计算
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == -999 || grid[r][c] != color)
            return 0;

        grid[r][c] = -999; //标记已经访问过
        int area = 1;
        for (int[] d : direction) {
            int nr = r + d[0];
            int nc = c + d[1];
            area += dfs(grid, nr, nc, color);
        }
        return area;
    }

    public static void main(String[] args) {
        FindMaxColorBlockSolution solution = new FindMaxColorBlockSolution();
        int[][] arr = new int[5][3];
        // 1 黄，2 黑，3 橙，4 蓝，5
        arr[0][0] = 1;
        arr[0][1] = 2;
        arr[0][2] = 1;

        arr[1][0] = 2;
        arr[1][1] = 3;
        arr[1][2] = 3;

        arr[2][0] = 2;
        arr[2][1] = 2;
        arr[2][2] = 2;

        arr[3][0] = 4;
        arr[3][1] = 4;
        arr[3][2] = 4;

        arr[4][0] = 1;
        arr[4][1] = 1;
        arr[4][2] = 2;

        System.out.println(solution.findMaxColorBlock(arr));

    }
}
