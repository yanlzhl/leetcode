package com.yanlz.algorith.tree;

/**
 * 98 https://leetcode.cn/problems/validate-binary-search-tree/
 * @author: Yan
 * @createTime: 2025/01/18 7:57 PM
 * @description:
 */
public class IsValidBST {
    public static void main(String[] args) {
        Solution9 solution = new Solution9();

//        // 示例二叉搜索树：
//        //       5
//        //      / \
//        //     3   7
//        //    / \ / \
//        //   1  4 6  8
//        TreeNode root1 = new TreeNode(5);
//        root1.left = new TreeNode(3);
//        root1.right = new TreeNode(7);
//        root1.left.left = new TreeNode(1);
//        root1.left.right = new TreeNode(4);
//        root1.right.left = new TreeNode(6);
//        root1.right.right = new TreeNode(8);
//
//        boolean isValid1 = solution.isValidBST(root1);
//        System.out.println("二叉树 1 是否为合法的二叉搜索树: " + isValid1); // 输出：true

        // 示例非二叉搜索树：
        //       5
        //      / \
        //     3   7
        //    / \ / \
        //   1  6 4  8
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(7);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(6);
        root2.right.left = new TreeNode(4);
        root2.right.right = new TreeNode(8);

        boolean isValid2 = solution.isValidBST(root2);
        System.out.println("二叉树 2 是否为合法的二叉搜索树: " + isValid2); // 输出：false
    }
}

class Solution9 {
    public boolean isValidBST(TreeNode root) {
        return _isValidBST(root, null, null);
    }

    // 定义：该函数返回 root 为根的子树的所有节点是否满足 max.val > root.val > min.val
    public boolean _isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        // 示例非二叉搜索树：
        //       5
        //      / \
        //     3   7
        //    / \ / \
        //   1  6 4  8

        // base case
        if (root == null) return true;
        // 若 root.val 不符合 max 和 min 的限制，说明不是合法 BST
        if (min != null && root.val <= min.val) return false;
        if (max != null && root.val >= max.val) return false;

        // 根据定义，限定左子树的最大值是 root.val，右子树的最小值是 root.val
        System.out.println("root.left: " +(root.left == null ? "null" : root.left.val)  + ", min: " + (min == null ? "null" : min.val) + ", max: " + (root == null ? "null" : root.val));
        boolean leftResult = _isValidBST(root.left, min, root);
        System.out.println("root.right: " + (root.right == null ? "null" : root.right.val)  + ", min: " + (root == null ? "null" : root.val) + ", max: " + (max == null ? "null" : max.val));
        boolean rightResult = _isValidBST(root.right, root, max);

        boolean result  = leftResult && rightResult;
        return result;
    }
}
