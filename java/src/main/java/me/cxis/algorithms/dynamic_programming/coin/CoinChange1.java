package me.cxis.algorithms.dynamic_programming.coin;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定不同面额的硬币coins和一个总金额amount，计算出可以凑成总金额的所需的最少的硬币个数。
 *
 * coins = [1,2,5] amount = 11 输出3（5+5+1=11）
 *
 * f(amount) = min{f(amount - 1), f(amount - 2), f(amount - 5)} + 1
 *
 * f(amount) = min{f(amount - coins(i))} + 1
 *
 *
 */
public class CoinChange1 {

    /**
     * 动态规划
     * @param amount
     * @param coins
     * @return
     */
    public static int coinChange(int amount, int[] coins) {

        // 凑够零钱i需要的最小值，i为0到amount
        int[] f = new int[amount + 1];
        f[0] = 0;
        for (int i = 1; i <= amount; i++) {
            f[i] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0 && f[i - coins[j]] != Integer.MAX_VALUE) {
                    f[i] = Math.min(f[i], f[i - coins[j]] + 1);
                }
            }
        }
        return f[amount];
    }

    /**
     * 递归
     * @param amount
     * @param coins
     * @return
     */
    public static int coinChange1(int amount, int[] coins) {
        if (amount == 0) {
            return 0;
        }

        if (amount < 0) {
            return -1;
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int sub = coinChange1(amount - coins[i], coins);
            if (sub == -1) {
                continue;
            }
            result = Math.min(sub + 1, result);
        }

        if (result == Integer.MAX_VALUE) {
            return -1;
        }
        return result;
    }

    /**
     * 备忘录模式
     * @param amount
     * @param coins
     * @return
     */
    public static int coinChange2(int amount, int[] coins, Map<Integer, Integer> map) {
        if (amount == 0) {
            return 0;
        }

        if (amount < 0) {
            return -1;
        }

        if (map.get(amount) != null) {
            return map.get(amount);
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int sub = coinChange2(amount - coins[i], coins, map);
            if (sub == -1) {
                continue;
            }
            result = Math.min(result, sub + 1);
        }

        if (result == Integer.MAX_VALUE) {
            return -1;
        }
        map.put(amount, result);
        return result;
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(coinChange1(amount, coins));
        System.out.println(coinChange2(amount, coins, new HashMap<Integer, Integer>()));
        System.out.println(coinChange(amount, coins));
    }
}
