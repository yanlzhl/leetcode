package com.yanlz.algorith.tree;

import java.util.List;
import java.util.ArrayList;

/**
 * @author: Yan
 * @createTime: 2025/01/12 1:05 PM
 * @description:
 */
public class MultiwayTree {

    public static void main(String[] args) {

        // 构建一个简单的多叉树
        Node nodeD = new Node(4, null);
        Node nodeE = new Node(5, null);
        Node nodeF = new Node(6, null);
        Node nodeB = new Node(2, new Node[]{nodeD, nodeE, nodeF});
        Node nodeC = new Node(3, null);
        Node root = new Node(1, new Node[]{nodeB, nodeC});

        traverse(root);
    }

    static void traverse(Node root) {
        // 示例二叉树：
        //       1
        //      /   \
        //     2     3
        //   / \ \
        //   4  5 6
        if (root == null) return;
        if (root.children != null) {
            for (Node child : root.children) {
                System.out.printf("从节点 %s 进入节点 %s\n", root.val, child.val);
                traverse(child);
                System.out.printf("从节点 %s 回到节点 %s\n", child.val, root.val);
            }
        }
    }
}

// 假设 Node 定义如下:
class Node {
    int val;
    Node[] children;

    Node(int val, Node[] children) {
        this.val = val;
        this.children = children;
    }
}
