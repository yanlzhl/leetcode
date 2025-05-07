package com.yanlz.algorith.tree;

/**
 * 每一条二叉树的「直径」长度，就是一个节点的左右子树的最大深度之和
 * @author: Yan
 * @createTime: 2025/01/12 12:27 PM
 * @description:
 */
public class DiameterOfBinaryTree {

    // 记录最大直径的长度
    int maxDiameter = 0;
    public static void main(String[] args) {
        DiameterOfBinaryTree solution = new DiameterOfBinaryTree();

        // 示例二叉树：
        //       1
        //      / \
        //     2   3
        //    / \
        //   4   5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        int diameter = solution.diameterOfBinaryTree(root);
        System.out.println("二叉树的直径为: " + diameter); // 输出：二叉树的直径为: 3
    }

    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return maxDiameter;
    }

    int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMax = maxDepth(root.left);
        int rightMax = maxDepth(root.right);
        // 后序位置，顺便计算最大直径
        int myDiameter = leftMax + rightMax;
        maxDiameter = Math.max(maxDiameter, myDiameter);

        return 1 + Math.max(leftMax, rightMax);
    }
}
