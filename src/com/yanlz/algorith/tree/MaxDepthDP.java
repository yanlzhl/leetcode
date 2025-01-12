package com.yanlz.algorith.tree;

/**
 * @author: Yan
 * @createTime: 2025/01/12 2:23 AM
 * @description:
 */
class MaxDepthDP {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        int maxDepth = maxDepth(root);
        System.out.println("二叉树的最大深度为: " + maxDepth);  // 输出：二叉树的最大深度为: 3
    }

    static int maxDepth(TreeNode root) {
        // 示例二叉树：
        //       3
        //      / \
        //     9  20
        //    /  /  \
        //   11 15  7
        if (root == null) {
            return 0;
        }
        // 递归计算左右子树的最大深度
        int leftMax = maxDepth(root.left);
        int rightMax = maxDepth(root.right);
        // 整棵树的最大深度就是左右子树的最大深度加一
        int res = Math.max(leftMax, rightMax) + 1;

        return res;
    }
}



//// 假设 TreeNode 定义如下:
//class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//    TreeNode() {}
//    TreeNode(int val) { this.val = val; }
//    TreeNode(int val, TreeNode left, TreeNode right) {
//        this.val = val;
//        this.left = left;
//        this.right = right;
//    }
//}
