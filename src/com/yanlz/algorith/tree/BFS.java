package com.yanlz.algorith.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Yan
 * @createTime: 2025/01/12 2:52 PM
 * @description:
 */
public class BFS {
    public static void main(String[] args) {
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

        levelTraverse(root);
    }

    static void levelTraverse(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        // 示例二叉树：
        //       1
        //      / \
        //     2   3
        //    / \   \
        //   4   5   6

        // 从上到下遍历二叉树的每一层
        while (!q.isEmpty()) {
            int sz = q.size();
            // 从左到右遍历每一层的每个节点
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                System.out.print(cur.val + " "); // 可以访问当前节点，这里是打印节点值
                // 将下一层节点放入队列
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
        }
    }
}
