package com.yanlz.algorith.tree;

import java.util.LinkedList;
import java.util.Queue;
/**
 * @author: Yan
 * @createTime: 2025/01/14 3:07 PM
 * @description:
 */
public class ConnectNodes {
    public static void main(String[] args) {
        Solution6 solution = new Solution6();
        // 示例二叉树：
        //       1
        //      / \
        //     2   3
        //    / \   \
        //   4   5   6
        //   /
        //   7
        ConnectNode root = new ConnectNode(1);
        root.left = new ConnectNode(2);
        root.right = new ConnectNode(3);
        root.left.left = new ConnectNode(4);
        root.left.right = new ConnectNode(5);
        root.right.right = new ConnectNode(6);
        root.left.left.left = new ConnectNode(7);

        ConnectNode connectedRoot = solution.connect(root);

        // 可以通过遍历测试每个节点的 next 指针是否指向了正确的节点
        printLevel(connectedRoot);
    }

    static void printLevel(ConnectNode root) {
        if (root == null) return;
        Queue<ConnectNode> q = new LinkedList<>();
        q.offer(root);

        // 从上到下遍历二叉树的每一层
        while (!q.isEmpty()) {
            int sz = q.size();
            // 从左到右遍历每一层的每个节点
            for (int i = 0; i < sz; i++) {
                ConnectNode cur = q.poll();
                System.out.print(cur.val + " --> " + (cur.next == null ? "#" : cur.next.val ) + " ,"); // 可以访问当前节点，这里是打印节点值
                // 将下一层节点放入队列
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            System.out.println();
        }
    }

}

class Solution6 {
    // 主函数
    public ConnectNode connect(ConnectNode root) {
        if (root == null) return null;
        // 遍历「三叉树」，连接相邻节点
        traverse(root.left, root.right);
        return root;
    }

    // 三叉树遍历框架
    void traverse(ConnectNode node1, ConnectNode node2) {
        // 示例二叉树：
        //       1
        //      / \
        //     2   3
        //    / \   \
        //   4   5   6
        //   /
        //   7
        if (node1 == null || node2 == null) {
            return;
        }
        // *** 前序位置 ***
        // 将传入的两个节点穿起来
        node1.next = node2;

        // 连接相同父节点的两个子节点
        traverse(node1.left, node1.right);
        traverse(node2.left, node2.right);
        // 连接跨越父节点的两个子节点
        traverse(node1.right, node2.left);
    }
}

// 假设 Node 定义如下:
class ConnectNode {
    int val;
    ConnectNode left;
    ConnectNode right;
    ConnectNode next;
    ConnectNode() {}
    ConnectNode(int val) { this.val = val; }
    ConnectNode(int val, ConnectNode left, ConnectNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
