package com.yanlz.algorith.linkedList;

/**
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

        ListNode result = mergeLindedLists(nodeList1,nodeList2);

//        for (ListNode node = result; result.next != null;  node = node.next){
//            System.out.println(node.val);
//        }
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
}
