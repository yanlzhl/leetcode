package com.yanlz.algorith.tree;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;


/**
 * 注意这一行： if (res.size() <= depth) {  //yan：很巧妙，如果 res.size() >= depth，说明已经至少存储了 该depth 层的第一个节点
 * @author: Yan
 * @createTime: 2025/01/12 9:34 PM
 * @description:
 */
public class BFS2 {
    public static void main(String[] args) {
        Solution solution = new Solution();
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
        System.out.println(result);
    }
}

class Solution {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> levelTraverse(TreeNode root) {
        if (root == null) {
            return res;
        }
        // root 视为第 0 层
        traverse(root, 0);
        return res;
    }

    void traverse(TreeNode root, int depth) {
        // 示例二叉树：
        //       1
        //      / \
        //     2   3
        //    / \   \
        //   4   5   6
        if (root == null) {
            return;
        }
        // 前序位置，看看是否已经存储 depth 层的节点了
        if (res.size() <= depth) {  //yan：很巧妙，如果 res.size() >= depth，说明已经至少存储了 该depth 层的第一个节点
            // 第一次进入 depth 层
            res.add(new LinkedList<>());
        }
        // 前序位置，在 depth 层添加 root 节点的值
        res.get(depth).add(root.val);
        traverse(root.left, depth + 1);
        traverse(root.right, depth + 1);
    }
}
