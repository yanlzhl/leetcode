package com.yanlz.algorith.tree;

/**
 * @author: Yan
 * @createTime: 2025/01/12 2:09 PM
 * @description:
 */
public class DFS {
    public static void main(String[] args) {
        int[][] grid = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        // 从所有点进行深度优先搜索，保证所有联通区域都能访问到
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    dfs(grid, i, j, visited);
                    System.out.println("当前区域遍历完毕");
                }
            }
        }
    }

    static void dfs(int[][] grid, int i, int j, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) {
            // 超出索引边界
            return;
        }
        if (visited[i][j]) {
            // 已遍历过 (i, j)
            return;
        }

        // 进入当前节点 (i, j)
        visited[i][j] = true;
        System.out.println("访问到: (" + i + "," + j + ") 值为：" + grid[i][j]);


        // 进入相邻节点（四叉树）
        // 上
        dfs(grid, i - 1, j, visited);
        // 下
        dfs(grid, i + 1, j, visited);
        // 左
        dfs(grid, i, j - 1, visited);
        // 右
        dfs(grid, i, j + 1, visited);
    }
}
