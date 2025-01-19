package com.yanlz.algorith.tree;

/**
 * @author: Yan
 * @createTime: 2025/01/19 7:05 PM
 * @description:
 */
public class GenerateTrees {
    public static void main(String[] args) {
        Solution10 solution = new Solution10();
        int n1 = 3;
        int result1 = solution.numTrees(n1);
        System.out.println("当 n = " + n1 + " 时，可以构成的二叉搜索树数量为: " + result1); // 输出：5

        int n2 = 4;
        int result2 = solution.numTrees(n2);
        System.out.println("当 n = " + n2 + " 时，可以构成的二叉搜索树数量为: " + result2); // 输出：14

        int n3 = 5;
        int result3 = solution.numTrees(n3);
        System.out.println("当 n = " + n3 + " 时，可以构成的二叉搜索树数量为: " + result3); // 输出：42
    }
}
class Solution10 {
    // 主函数
    public int numTrees(int n) {
        // 计算闭区间 [1, n] 组成的 BST 个数
        return count(1, n);
    }

    // 计算闭区间 [lo, hi] 组成的 BST 个数
    int count(int lo, int hi) {
        // base case
        if (lo > hi) return 1;

        int res = 0;
        for (int i = lo; i <= hi; i++) {
            // i 的值作为根节点 root
            int left = count(lo, i - 1);
            int right = count(i + 1, hi);
            // 左右子树的组合数乘积是 BST 的总数
            res += left * right;
        }

        return res;
    }
}
