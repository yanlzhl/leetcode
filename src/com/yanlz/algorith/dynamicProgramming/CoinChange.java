package com.yanlz.algorith.dynamicProgramming;

/**
 * @author: Yan
 * @createTime: 2024/12/14 3:21 PM
 * @description:
 *
 * 我认真在ipad上画了下递归树，发现有很多重叠子问题，所以可以用动态规划来解决。
 * 其中理解是否还有硬币来凑，是通过dp(coins, amount - coin)中的返回值来判断，-1就是不可以找了，0就是找到了所有的凑整硬币。
 * +1 是代表一个满足凑整硬币的个数，加一起作用是在其dp(coins, amount - coin)返回为0时才有真正意义。
 * 以dp({1, 2, 3}, 5)为例，第一个完整min应该是5，每次for (int coin : coins) 都是先coin=1，及5次递归。
 * dp(int[] coins, int amount)
 *
 * dp({1, 2, 3}, 5)
 *
 *
 */
public class CoinChange {
    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        int[] coins = {1, 2, 3};
        System.out.println(coinChange.dp(coins, 5));;
    }

    // 定义：要凑出金额 n，至少要 dp(coins, n) 个硬币
    public int dp(int[] coins, int amount) {
        // base case
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            // 计算子问题的结果
            int subProblem = dp(coins, amount - coin);
            // 子问题无解则跳过
            if (subProblem == -1) continue;
            // 在子问题中选择最优解，然后加一
            res = Math.min(res, subProblem + 1);
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
