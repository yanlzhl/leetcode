package com.yanlz.algorith.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class Backtrack {

    static boolean[] used;
    static LinkedList<Integer> track = new LinkedList<>();
    static List<List<Integer>> res = new ArrayList<>();


    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        used = new boolean[nums.length];

        backtrack(nums);

        System.out.println("所有的结果：");
        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }

    static void backtrack(int[] nums) {

        //  递归终止条件：当 track 中的元素数量达到数组长度时，表示找到一条有效的路径
        if (track.size() == nums.length) {
            res.add(new ArrayList<>(track)); //  将当前路径添加到结果集
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) { // 如果当前元素已经使用过，则跳过
                continue;
            }

            // 做选择
            used[i] = true;
            track.addLast(nums[i]);

            // 进入下一层回溯树
            backtrack(nums);

            // 取消选择
            track.removeLast();
            used[i] = false;
        }
    }
}