package com.yanlz.algorith.tree;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * @author: Yan
 * @createTime: 2025/01/12 10:50 PM
 * @description:
 */
public class BFS3 {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        // 示例二叉树：
        //       1
        //      / \
        //     2   3
        //    / \   \
        //   4   5   6
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        List<List<Integer>> result = solution.levelTraverse(root);
        System.out.println("自顶向下遍历结果");
        System.out.println(result);

//        List<List<Integer>> result2 = solution.levelTraverse2(root);
//        System.out.println("自底向上遍历结果");
//        System.out.println(result2);

    }
}

class Solution3 {

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> levelTraverse(TreeNode root) {
        if (root == null) {
            return res;
        }
        List<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        traverse(nodes);
        return res;
    }

    public List<List<Integer>> levelTraverse2(TreeNode root) {
        List<List<Integer>> res2 = new LinkedList<>();
        if (root == null) {
            return res2;
        }
        List<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        traverse2(nodes, res2);
        return res2;
    }

    void traverse(List<TreeNode> curLevelNodes) {
        // base case
        if (curLevelNodes.isEmpty()) {
            return;
        }

        // 示例二叉树：
        //       1
        //      / \
        //     2   3
        //    / \   \
        //   4   5   6

        // 前序位置，计算当前层的值和下一层的节点列表
        List<Integer> nodeValues = new LinkedList<>();
        List<TreeNode> nextLevelNodes = new LinkedList<>();
        for (TreeNode node : curLevelNodes) {
            nodeValues.add(node.val);
            if (node.left != null) {
                nextLevelNodes.add(node.left);
            }
            if (node.right != null) {
                nextLevelNodes.add(node.right);
            }
        }
        // 前序位置添加结果，可以得到自顶向下的层序遍历
        res.add(nodeValues);
        traverse(nextLevelNodes);
        // 后序位置添加结果，可以得到自底向上的层序遍历结果
        // res.add(nodeValues);
    }


    void traverse2(List<TreeNode> curLevelNodes,  List<List<Integer>> res2) {
        // base case
        if (curLevelNodes.isEmpty()) {
            return;
        }
        // 前序位置，计算当前层的值和下一层的节点列表
        List<Integer> nodeValues = new LinkedList<>();
        List<TreeNode> nextLevelNodes = new LinkedList<>();
        for (TreeNode node : curLevelNodes) {
            nodeValues.add(node.val);
            if (node.left != null) {
                nextLevelNodes.add(node.left);
            }
            if (node.right != null) {
                nextLevelNodes.add(node.right);
            }
        }

        traverse2(nextLevelNodes, res2);
        // 后序位置添加结果，可以得到自底向上的层序遍历结果
        res2.add(nodeValues);
    }
}
