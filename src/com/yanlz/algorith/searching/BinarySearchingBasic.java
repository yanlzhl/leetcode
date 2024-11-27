package com.yanlz.algorith.searching;

/**
 * 二分查找 请参考 https://labuladong.online/algo/essential-technique/binary-search-framework/#%E5%9B%9B%E3%80%81%E9%80%BB%E8%BE%91%E7%BB%9F%E4%B8%80
 * 考虑了常规的二分查找，左右边界查找，比较容易记忆的是左闭右闭区间，即[left,right]。因为是闭合，taget != nums[mid]是，分别在左右边界各+1或-1，这种规律比较容易记忆。
 *
 * @author: Yan
 * @createTime: 2024/11/27 2:04 PM
 * @description: difficulty: easy
 */
public class BinarySearchingBasic {

    public static void main(String[] args) {
//        int[] numbers = new int[]{1,2,3,5,5,5,7,8,9};
        int[] numbers = new int[]{1,2,3,4,5,6,8,9,10,11};
        BinarySearchingBasic binarySearchingBasic = new BinarySearchingBasic();
        System.out.println(binarySearchingBasic.binary_search(numbers,7));

        System.out.println(binarySearchingBasic.left_bound(numbers,5));

        System.out.println(binarySearchingBasic.right_bound(numbers,5));

        System.out.println(binarySearchingBasic.searchInsertPosition( new int[]{1,3,5,6},7));
    }

    /**
     * 二分查找 对查找的数组进行二分查找的前提是数组是有序的
     * 704 https://leetcode.cn/problems/binary-search/submissions/583440712/
     * @param nums
     * @param target
     * @return
     */
    int binary_search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if(nums[mid] == target) {
                System.out.println("left:"+left);
                System.out.println("right:"+right);
                // 直接返回
                return mid;
            }
        }

        System.out.println("-1 left:"+left);
        System.out.println("-1 right:"+right);

        // 直接返回
        return -1;
    }

    int left_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定左侧边界
                right = mid - 1;
            }
        }
        // 判断 target 是否存在于 nums 中
        if (left < 0 || left >= nums.length) {
            return -1;
        }
        // 判断一下 nums[left] 是不是 target
        return nums[left] == target ? left : -1;
    }

    int right_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定右侧边界
                left = mid + 1;
            }
        }
        // 由于 while 的结束条件是 right == left - 1，且现在在求右边界
        // 所以用 right 替代 left - 1 更好记
        if (right < 0 || right >= nums.length) {
            return -1;
        }
        return nums[right] == target ? right : -1;
    }

    /**
     * 35 https://leetcode-cn.com/problems/search-insert-position/
     * 二分查找的变种，找到插入位置。这个题目让自己清楚，left和right的含义，相对于target的位置，left左边的值一直保持小于target，right右边的值一直保持大于等于target。
     * 不需要ans变量，最后直接返回left就可以了，
     * 根据if的判断条件，left左边的值一直保持小于target，right右边的值一直保持大于等于target，如果没有找到target，而且left最终一定等于right+1。找到了target，left和right值看情况。
     * @param nums
     * @param target
     * @return
     */
    public int searchInsertPosition(int[] nums, int target){
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if(nums[mid] == target) {
                // 直接返回
                return mid;
            }
        }
        // 直接返回
        return left;
    }


}

