package com.yanlz.algorith.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * remove-duplicates
 *
 * @author: Yan
 * @createTime: 2024/02/21 17:32
 * @description:
 * 26: https://leetcode.cn/problems/remove-duplicates-from-sorted-array/  删除有序数组中的重复项
 * 83: https://leetcode.cn/problems/remove-duplicates-from-sorted-list/description/ 删除排序链表中的重复元素
 * 27: https://leetcode.cn/problems/remove-element/description/ 移除某一相同元素，返回其他不同元素的个数
 */
public class RemoveDuplicates_array_26 {
    public static void main(String[] args) {

        // entrance of 26
        int[] removeDuplicatesArray = new int[]{1,2,3,3,3,4,5};
        //System.out.println(removeDuplicates(removeDuplicatesArray));

        // entrance of 83
//        ListNode nodeFive = new ListNode(3,new ListNode());
//        ListNode nodeFour = new ListNode(3,nodeFive);
//        ListNode nodeThree = new ListNode(2,nodeFour);
//        ListNode nodeSecond = new ListNode(1,nodeThree);
//        ListNode nodeHead = new ListNode(1,nodeSecond);
//        System.out.println(deleteDuplicates(nodeHead));

        // entrance of 27
        int[] removeElement = new int[]{1,2,3,3,3,4,5,6};
        System.out.println(removeElement(removeElement,3));
    }

    /**
     * 由于数组已经排序，所以重复的元素一定连在一起，让慢指针 slow 走在后面，快指针 fast 走在前面探路，找到一个不重复的元素就赋值给 slow 并让 slow 前进一步。
     * 这样，就保证了 nums[0..slow] 都是无重复的元素，当 fast 指针遍历完整个数组 nums 后，nums[0..slow] 就是整个数组去重之后的结果。
     * @param originalArray
     * @return
     */
    public static int removeDuplicates(int[] originalArray){
        if (originalArray ==  null || originalArray.length == 0){
            return 0;
        }

        int slow = 0;
        int fast = 0; //为0，1都可

        while (fast < originalArray.length){
            // 比较后两个数据不一样，slow指针才递增1，否则就是fast++向前推进
            if (originalArray[fast] != originalArray[slow]){
                slow++;
                originalArray[slow] = originalArray[fast];
            }

            fast++;
        }

        // 截取打印验证下
//        int[] result = Arrays.copyOfRange(originalArray,0, slow+1);
//        for (int i = 0; i < result.length; i++) {
//            System.out.println(result[i]);
//        }

        return slow + 1;
    }

    /**
     * 和 @removeDuplicates 的目的一样，只是操作的数据结构从数组变为链表
     *
     * @param listNode
     * @return
     */
    public static ListNode deleteDuplicates(ListNode listNode) {
        if (listNode == null || listNode.next == null){
            return null;
        }

        ListNode slow = listNode;
        ListNode fast = listNode.next;

        while (fast.next != null){
            if (fast.val != slow.val){
                //将重复元素对下一个数据向后移动覆盖前一个重复对
                // nums[slow] = nums[fast];
                slow.next = fast;
                //慢指针向前一步，放在上一行代码后才可衔接上，这一步容易忘（以为移动了）。
                // slow++;
                slow = slow.next;
            }
            // fast++
            fast = fast.next;
        }

        slow.next = null;

        // to print
//        ListNode node = listNode;
//        List<ListNode> list = new ArrayList<>();
//        list.add(node);
//        while (node.next !=null){
//            node = node.next;
//            list.add(node);
//        }
//        for (ListNode item : list){
//            System.out.println(item.val);
//        }


        return listNode;
    }

    /**
     *
     * 通过快指针和目标值来比较进行统计剩下的元素个数。同时，在原有的数组中维护新的元素在里面。
     * 当快指针和目标值相同时，快指针移动到下一个，而慢指针不动；
     * 当快指针和目标值不相同时，慢指针赋值该不同的元素值，然后慢指针的数+1，快指针移动到下一个；重复上一步
     * 1，1，2，3；1
     * 1，2，3，3，2；2
     *
     * @param nums 列表
     * @param targetValue 目标值
     * @return
     */
    public static int removeElement(int[] nums, int targetValue){
        if ( nums == null || nums.length == 0){
            return 0;
        }

        int slow = 0;
        int fast = 0;

        while (fast < nums.length){
           if (nums[fast] != targetValue){
               nums[slow] = nums[fast];
               slow++;
           }

           fast++;
        }

//        for (int i = 0; i < slow; i++) {
//            System.out.println(nums[i]);
//        }

        return slow;
    }

}


class ListNode {
  int val;
  ListNode next;
  ListNode() {}
  ListNode(int val) {
      this.val = val;
  }
  ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
  }
}

