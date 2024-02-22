package com.yanlz.algorith.searching;

import java.util.Arrays;

/**
 * @author: Yan
 * @createTime: 2024/02/22 13:29
 * @description: Binary Searching
 * 二分查找是左右指针的常用算法体现
 *  167 https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/description/
 *  344 https://leetcode.cn/problems/reverse-string/
 */
public class BinarySearching {
    public static void main(String[] args) {

//        int[] numbers = new int[]{1,2,3,4,5,6,7,8,9};
//        System.out.println(binarySearching(numbers,9));

        //167
        //int[] numbers = new int[]{2,7,11,16};
        //Arrays.stream(twoSum(numbers,18)).forEach(n-> System.out.println(n));

        //344
//        reverseArray(new int[]{2,7,11,16});
        reverseArray(new char[]{'a','b','c','d'});
    }

    /**
     *  Binary Searching
     * @param numbers numbers list
     * @param target target value
     * @return
     */
    public static int binarySearching(int[] numbers, int target){
        int left=0;
        int right= numbers.length - 1;

        while (left <= right){  //无法再继续二分的条件
            int middle = (left + right) / 2; //middle是通过left，right每一次比较后根据条件调整的，必须是再while中
            if (numbers[middle] == target){
                return middle;
            } else if (numbers[middle] < target) {
                left = middle + 1;
            } else if (target < numbers[middle]) {
                right = middle -1;
            }
        }

        return -1;
    }

    /**
     * 167 https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/
     * 只要数组有序，就应该想到双指针技巧
     * 一个方法团灭 nSum 问题  https://labuladong.online/algo/practice-in-action/nsum/
     *
     * @param numbers numbers list
     * @param target target value
     * @return
     */
    public static int[] twoSum(int[] numbers, int target){
        int left=0;
        int right= numbers.length - 1;

        while (left < right){  //无法再继续二分的条件
            int sum = numbers[left] + numbers[right];
            if (sum == target){
                return new int[]{left+1, right+1};
            } else if (sum < target) {
                left++;
            } else if (target < sum) {
                right--;
            }
        }

        return new int[]{-1,-1};
    }

    /**
     * 344 https://leetcode.cn/problems/reverse-string/
     * @param numbers
     */
    public static void reverseArray(int[] numbers){
        int left = 0;
        int right = numbers.length-1;
        while (left < right){
            int temp = numbers[left];
            numbers[left] = numbers[right];
            numbers[right] = temp;
            left++;
            right--;
        }
        Arrays.stream(numbers).forEach(n-> System.out.println(n));
    }

    /**
     * 344 https://leetcode.cn/problems/reverse-string/
     * @param numbers
     */
    public static void reverseArray(char[] numbers){
        int left = 0;
        int right = numbers.length-1;
        while (left < right){
            char temp = numbers[left];
            numbers[left] = numbers[right];
            numbers[right] = temp;
            left++;
            right--;
        }
        for (char c : numbers) {
            System.out.print(c + " ");
        }
    }

    /**
     * 回文串判断
     *
     * @param s 回文串
     * @return
     */
    public static boolean isPalindrome(String s) {
        char[] charArray =  s.toCharArray();

        int left = 0;
        int right = charArray.length-1;
        while (left < right){
            if (charArray[left] != charArray[right]){
                return false;
            }

            left++;
            right--;
        }

        return true;
    }



}
