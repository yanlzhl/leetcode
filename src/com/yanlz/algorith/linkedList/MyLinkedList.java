package com.yanlz.algorith.linkedList;

/**
 * https://labuladong.online/algo/data-structure-basic/linkedlist-implement/#%E5%8F%8C%E9%93%BE%E8%A1%A8%E4%BB%A3%E7%A0%81%E5%AE%9E%E7%8E%B0
 *
 * @author: Yan
 * @createTime: 2024/04/08 16:36
 * @description: LinkedList implement by myself
 */
public class MyLinkedList {

    // 虚拟头尾节点
    final private Node head, tail;
    private int size;

    // 双链表节点
    //JAVA泛型通配符T，E，K，V区别  https://www.jianshu.com/p/95f349258afb
    private static class Node<E> {
        E val;
        Node<E> next;
        Node<E> prev;

        Node(E val) {
            this.val = val;
        }
    }

    // 构造函数初始化虚拟头尾节点
    public MyLinkedList() {
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        head.next = tail;
        tail.prev = head;
        this.size = 0;
    }

    public void addLast(Node e) {

    }

    public void addFirst(Node e) {

    }

    public void add(int index, Node element) {

    }

    public Node removeFirst() {
        return null;
    }

    public Node removeLast() {
        return null;
    }

    public Node remove(int index) {
        return null;
    }

}
