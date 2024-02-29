package com.yanlz.algorith.searching;

import java.util.Arrays;

/**
 * @author: Yan
 * @createTime: 2024/02/22 13:29
 * @description: Binary Searching
 * 二分查找是左右指针的常用算法体现
 *  167 https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/description/
 *  344 https://leetcode.cn/problems/reverse-string/
 *  5 https://leetcode.cn/problems/longest-palindromic-substring/description/
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
//        reverseArray(new char[]{'a','b','c','d'});

        //5 cabcbad eabaabaf
        System.out.println(findTheLongestPalindrome("eabaabaf"));;
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

    /**
     * 找到符合回文串--再外层再做比较
     * left right再奇数字符串中起始点是一样的；abcba
     * left right偶数串中，起始点不重合，right 再left右边1位  abaaba
     * @param string
     * @param left 左侧开始出，递减向左移动
     * @param right 右侧开始出，递减向右移动
     * @return
     */
    private static String findThePalindrome(String string,int left, int right){
        while (left>=0 && right < string.length() && (string.charAt(left) == string.charAt(right))){
            left--;
            right++;
        }

        //此处需要特别注意，因为left--，right++运算在先，
        // 而substring中，the beginning index, inclusive，the ending index, exclusive.
        //因此left+1，right无需要再+1了
        return string.substring(left+1,right);
    }

    /**
     *  5 https://leetcode.cn/problems/longest-palindromic-substring/description/
     * @param string Palindrome
     * @return
     */
    public  static String findTheLongestPalindrome(String string){
        String longestSubPalindrome = "";
        for (int i = 0; i < string.length(); i++) {
            String oddStr = findThePalindrome(string,i,i);
            String evenStr = findThePalindrome(string,i,i+1);

            //本轮最长的
            String tempLongest = oddStr.length() > evenStr.length() ? oddStr:evenStr;
            //再和上一轮最长的比
            longestSubPalindrome = tempLongest.length() > longestSubPalindrome.length() ?  tempLongest:longestSubPalindrome;
        }
        return longestSubPalindrome;
    }


}
