package com.yanlz.algorith.linkedList;

/**
 *
 * 21 合并两个有序链表
 *
 * @author: Yan
 * @createTime: 2024/04/09 10:58
 * @description:
 */
public class LinkedListOperation {
    public static void main(String[] args) {
        ListNode nodeFive = new ListNode(8,new ListNode());
        ListNode nodeFour = new ListNode(7,nodeFive);
        ListNode nodeThree = new ListNode(6,nodeFour);
        ListNode nodeSecond = new ListNode(5,nodeThree);
        ListNode nodeList1 = new ListNode(1,nodeSecond);

        ListNode nodeFive2 = new ListNode(5,new ListNode());
        ListNode nodeFour2 = new ListNode(4,nodeFive2);
        ListNode nodeThree2 = new ListNode(3,nodeFour2);
        ListNode nodeSecond2 = new ListNode(2,nodeThree2);
        ListNode nodeList2 = new ListNode(1,nodeSecond2);

//        ListNode result = mergeLindedLists(nodeList1,nodeList2);

        ListNode result = reverse(nodeList1);
        while (result!=null){
            if (result.val != 0){ //处理没有值的节点，因为默认是0
                System.out.println(result.val);
            }
            result = result.next;
        }





    }

    /**
     * 合并两个有序链表
     * 21 https://leetcode.cn/problems/merge-two-sorted-lists/
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeLindedLists(ListNode l1,ListNode l2){

        // 虚拟头结点
        ListNode dummy = new ListNode(-1), p = dummy;
        while (l1 != null && l2 != null) {
            //P优先指向数值小的节点 也就是即将要合并到P的节点，因为从小到大合并
            if (l1.val > l2.val){
                p.next = l2;
                l2 = l2.next;
            }else {
                p.next = l1;
                l1 = l1.next;
            }
            p = p.next;
        }

        if (l1 != null){
            p.next = l1;
        }

        if (l2 != null){
            p.next = l2;
        }

        return dummy.next;
    }

    static ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode(-1), p1 = dummy;
        ListNode  p2 = dummy;

        while (head != null){
            if (head.val > x){
                p2.next = head;
                p2 = p2.next;
            }else {
                p1.next = head;
                p1 = p1.next;
            }
            head = head.next;

            // 注意：不能直接让 p 往后走，否则链表中会出现环，也就是原链表倒数第二个节点会指向倒数第一个（且倒数2个节点拆分在P1和P2上）
            ListNode temp = head.next;
            temp.next= null;
            head = temp;
        }

        p1.next = p2.next;
        return dummy.next;
    }

    //  206 递归反转整个链表 https://leetcode.cn/problems/reverse-linked-list/
    // 定义：输入一个单链表头结点，将该链表反转，返回新的头结点  1 5 6 7 8
    static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

}
