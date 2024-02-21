package com.yanlz.algorith.array;

import java.util.Arrays;

public class RemoveDuplicates_array_26 {
    public static void main(String[] args) {
        int[] removeDuplicatesArray = new int[]{1,2,3,3,3,4,5};
        System.out.println(removeDuplicates(removeDuplicatesArray));
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
        int[] result = Arrays.copyOfRange(originalArray,0, slow+1);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }

        return slow + 1;
    }
}
