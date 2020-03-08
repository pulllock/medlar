package me.cxis.algorithms.dynamic_programming.perfect_squares;

/**
 * 给定一个正整数n
 * 问最少可以将n分成几个完全平方数之和
 *
 * 输入：13
 * 输出2（13 = 4 + 9）
 *
 * 确定状态：
 * 最后一步：最优策略中最后一步一个完全平方数j的平方
 * 最优策略中n-（j的平方）也一定被划分成最少的完全平方数之和
 *
 * 需要知道n-（j的平方）最少被划分成几个完全平方数之和
 * 原问题：求n最少被划分成几个完全平方数之和
 *
 * 状态：设f[i]表示i最少被分成几个完全平方数之和
 *
 * f[i] = min{f[i-j*j + 1} 1<=j*j<=i
 *
 * 初始条件
 * f[0] = 0
 */
public class PrefectSquares {

    public static int prefectSquares(int n) {
        int[] f = new int[n + 1];
        f[0] = 0;

        for (int i = 1; i <= n; i++) {
           f[i] = Integer.MAX_VALUE;
           for (int j = 1; j * j <= i; j++) {
                f[i] = Math.min(f[i - j * j] + 1, f[i]);
           }
        }
        return f[n];
    }

    public static void main(String[] args) {
        int n = 13;
        System.out.println(prefectSquares(n));
    }
}
