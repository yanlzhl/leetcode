package com.yanlz.algorith.tree;

/**
 * @author: Yan
 * @createTime: 2025/01/14 4:26 PM
 * @description:
 */
public class FlattenRoot {
    public static void main(String[] args) {
        Solution8 solution = new Solution8();
        // 示例二叉树：
        //       1
        //      / \
        //     2   5
        //    / \   \
        //   3   4   6
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        solution.flatten(root);

        printTree(root); // 打印转化后的链表
    }

    static void printTree(TreeNode root){
        while(root != null){
            System.out.print(root.val + "->");
            root = root.right;
        }
        System.out.print("null");
    }
}

class Solution8 {
    // 定义：将以 root 为根的树拉平为链表
    public void flatten(TreeNode root) {
        // 示例二叉树：
        //       1
        //      / \
        //     2   5
        //    / \   \
        //   3   4   6
        // base case
        if (root == null) return;

        // 利用定义，把左右子树拉平
        flatten(root.left);
        flatten(root.right);

        // *** 后序遍历位置 ***
        // 1、左右子树已经被拉平成一条链表
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 2、将左子树作为右子树
        root.left = null;
        root.right = left;

        // 3、将原先的右子树接到当前右子树的末端
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
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
