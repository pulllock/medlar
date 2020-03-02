package me.cxis.algorithms.dynamic_programming.maximun_product_subarray;

/**
 * 给定数组a[0]...a[n-1]
 * 找到最长的连续子序列i,i+1,i+2...j，使a[i] * a[i+1] * ... * a[j]最大
 *
 * [2, 3, -2, 4] -> 6 (2 * 3 = 6)
 *
 * 最值型动态规划
 *
 * 问题：以a[j]结尾的连续子序列乘积要最大
 *
 * 确定状态：
 * 最后一步：对于最优策略乘积最大，一定有最后一个元素a[j]
 * 1. 连续子序列长度等于1，结果就是a[j]
 * 2. 连续子序列长度大于1，最优策略中a[j]前一个元素一定都是a[j-1]
 *
 * 如果a[j]是正数，那么以a[j-1]结尾的连续子序列乘积要最大
 * 如果a[j]是负数，那么以a[j-1]结尾的连续子序列乘积要最小
 *
 * 子问题：求以a[j-1]结尾的连续子序列乘积要最大/最小
 *
 * 状态：
 * 设f[j]=以a[j]结尾的连续子序列的最大乘积，g[j]=以a[j]结尾的连续子序列的最小乘积
 *
 * 状态转移方程：
 * f[j]=以a[j]结尾的连续子序列的最大乘积
 * f[j]=max{a[j], max{a[j] * f[j-1], a[j] * g[j]}} j>0
 *
 * g[j]=以a[j]结尾的连续子序列的最小乘积
 * g[j]=min{a[j], min{a[j] * f[j-1], a[j] * g[j-1]}} j>0
 *
 * 初始条件和边界情况：
 * j > 0
 *
 * 计算顺序：f[0] g[0] f[1] g[1]...f[n-1] g[n-1]
 *
 * 结果max{f[0], f[1],..., f[n-1]}
 *
 */
public class MaximumProductSubarray {

    public int maxProduct(Integer[] a) {
        int n = a.length;
        if (n == 0) {
            return 0;
        }

        int[] f = new int[n];
        int[] g = new int[n];

        int res = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            f[i] = a[i];
            if (i > 0) {
                f[i] = Math.max(f[i], Math.max(a[i] * f[i-1], a[i] * g[i-1]));
            }

            g[i] = a[i];
            if (i > 0) {
                g[i] = Math.min(f[i], Math.min(a[i] * f[i-1], a[i] * g[i-1]));
            }

            res = Math.max(res, f[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        Integer[] a = {2, 3, -2, 4};
        MaximumProductSubarray subarray = new MaximumProductSubarray();
        System.out.println(subarray.maxProduct(a));

        a = new Integer[]{2, 3, 5, -2, 4, 8};
        System.out.println(subarray.maxProduct(a));
    }
}
