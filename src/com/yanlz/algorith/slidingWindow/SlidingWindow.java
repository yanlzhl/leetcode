package com.yanlz.algorith.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 76 https://leetcode.cn/problems/minimum-window-substring/description/
 * 576 https://leetcode.cn/problems/permutation-in-string/description/
 *
 * @author: Yan
 * @createTime: 2024/02/26 15:43
 * @description:
 */
public class SlidingWindow {
    public static void main(String[] args) {
//        System.out.println(minWindow("ADOBECODEBANC",  "ABC"));
//        System.out.println(minWindow("ABDCABC",  "ABC"));
//        System.out.println(minWindow("BBACF",  "ABBC"));
        System.out.println(finePermutationInString("BBAFF","AF"));
    }

    /**
     * 最小子串问题中
     * 首先的问题是如何判断目标子串是否被窗口包含，思路是统计目标子串中每个字符出现的个数，然后比对每个字符的个数必须是一样的。
     */
    public static String minWindow(String string, String targetStr){
        Map<Character,Integer> need = new HashMap<>();
        Map<Character,Integer> window = new HashMap<>();

        // to statistic the count of each char of  the target string
        for (char c : targetStr.toCharArray()){
            need.put(c,need.getOrDefault(c,0)+1);
        }

        int left = 0, right = 0;
        int valid = 0; // 窗口中满足需要的字符个数,比如target= abcd,那么valid=4是是符合子串要求的
        int length =  Integer.MAX_VALUE; // 此处给一个最大值的意思是窗口的无限大，以避免某些情况下字符过长的问题，因此给一个不太可能达到的长度
        int start = 0; //标记最小窗口的起始位置
        // 统计窗口中在 目标window中字符的个数
        // 比较步骤拆分：1. 统计该字符是否包含，2在包含的前提下，更新该窗口下次字符的个数 3与目标窗口的字符的数量比较，符合的情况下更新valid数
        while (right < string.length()){
            char c = string.charAt(right);
            right++;
            if (need.containsKey(c)){
                window.put(c, window.getOrDefault(c,0)+1);
                if (window.get(c).equals(need.get(c))){
                    valid++;
                }
            }

            //ADOBECODBBANC  DOB  1，3

            //左侧窗口缩小的条件
            while (valid == need.size()){  //此处停止条件需要注意 别写成if
                //此处记录最小子串口对位置
                if (right - left < length){
                    start =  left;
                    length = right - left;
                }

                //统计缩小后的窗口是否依然包含目标子串中字符的个数
                char leftChar = string.charAt(left);
                left++; //注意这个语句的位置，在leftchar之后
                if (need.containsKey(leftChar)){

                    //此处需要注意和下一行代码的关系，先去比较是否相等，如果相等，说明该字符是有用不可删除的，并打破了最小窗口的要求，valid需要-1
                    if (window.get(leftChar).equals(need.get(leftChar))) {
                        //此处如果是相等 一定要先讲valid-1 再去更新leftChar的数值 不然left会越界
                        valid--;
                    }

                    //这行代码的作用是将窗口中字符 leftChar 的出现次数减一。在收缩窗口时，左指针 left 移出窗口的字符 leftChar 在 window 中的出现次数需要减一。
                    window.put(leftChar,window.get(leftChar)-1);
                }

            }
        }

        //ADOBECODBBANC  DOB  1，3
        //需要判断是否存在的问题，如果没有找到注意字符串截取边界溢出问题
        return length ==Integer.MAX_VALUE ? "": string.substring(start,start+length);
    }

    /**
     * 字符串的排列
     * 2个问题：
     * 何时停止右边窗口的增加？
     *  当窗口的长度等于targetStr的长度，相当于是等长的窗口
     * 如何停止左边窗口的增加？
     */
    public static boolean finePermutationInString(String string, String targetStr){
        boolean result = Boolean.FALSE;

        Map<Character,Integer> need = new HashMap<>();
        Map<Character,Integer> window = new HashMap<>();

        // to statistic the count of each char of  the target string
        for (char c : targetStr.toCharArray()){
            need.put(c,need.getOrDefault(c,0)+1);
        }

        int left = 0, right = 0;
        int valid = 0; // 窗口中满足需要的字符个数,比如target= abcd,那么valid=4是是符合子串要求的
        int length =  Integer.MAX_VALUE; // 此处给一个最大值的意思是窗口的无限大，以避免某些情况下字符过长的问题，因此给一个不太可能达到的长度
        int start = 0; //标记最小窗口的起始位置
        // 统计窗口中在 目标window中字符的个数
        // 比较步骤拆分：1. 统计该字符是否包含，2在包含的前提下，更新该窗口下次字符的个数 3与目标窗口的字符的数量比较，符合的情况下更新valid数
        while (right < string.length()){
            char c = string.charAt(right);
            right++;
            if (need.containsKey(c)){
                window.put(c, window.getOrDefault(c,0)+1);
                if (window.get(c).equals(need.get(c))){
                    valid++;
                }
            }

            //"BBAFF","AF"

            //左侧窗口缩小的条件 大于等于等长的窗口
            while (right - left >= targetStr.length()){
                //当发现 valid == need.size() 时，就说明窗口中就是一个合法的排列，所以立即返回 true。
                if (valid == need.size()){
                    result = Boolean.TRUE;
                    return result;
                }

                //统计缩小后的窗口是否依然包含目标子串中字符的个数
                char leftChar = string.charAt(left);
                left++; //注意这个语句的位置，在leftchar之后
                if (need.containsKey(leftChar)){

                    //此处需要注意和下一行代码的关系，先去比较是否相等，如果相等，说明该字符是有用不可删除的，并打破了最小窗口的要求，valid需要-1
                    if (window.get(leftChar).equals(need.get(leftChar))) {
                        //此处如果是相等 一定要先讲valid-1 再去更新leftChar的数值 不然left会越界
                        valid--;
                    }

                    //这行代码的作用是将窗口中字符 leftChar 的出现次数减一。在收缩窗口时，左指针 left 移出窗口的字符 leftChar 在 window 中的出现次数需要减一。
                    window.put(leftChar,window.get(leftChar)-1);
                }

            }
        }

        //ADOBECODBBANC  DOB  1，3
        //需要判断是否存在的问题，如果没有找到注意字符串截取边界溢出问题
        return result;
    }

}
