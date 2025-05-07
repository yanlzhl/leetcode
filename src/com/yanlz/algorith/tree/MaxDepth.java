package com.yanlz.algorith.tree;

/**
 * @author: Yan
 * @createTime: 2025/01/12 2:23 AM
 * @description:
 */
class MaxDepth {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();

        // 示例二叉树：
        //       3
        //      / \
        //     9  20
        //    /  /  \
        //   11 15  7
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        int maxDepth = solution.maxDepth(root);
        System.out.println("二叉树的最大深度为: " + maxDepth); // 输出：二叉树的最大深度为: 3
    }
}

class Solution2 {

    // 记录最大深度
    int res = 0;
    // 记录当前遍历节点的深度
    int depth = 0;

    // 主函数
    int maxDepth(TreeNode root) {
        traverse(root);
        return res;
    }

    // 二叉树遍历框架
    void traverse(TreeNode root) {
        if (root == null) {
            // 到达叶子节点
            res = Math.max(res, depth);
            return;
        }
        // 前序遍历位置
        depth++;
        traverse(root.left);
        traverse(root.right);
        // 后序遍历位置
        depth--;
    }
}

// 假设 TreeNode 定义如下:
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
