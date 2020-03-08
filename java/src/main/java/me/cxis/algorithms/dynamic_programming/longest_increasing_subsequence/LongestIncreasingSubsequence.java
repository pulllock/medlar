package me.cxis.algorithms.dynamic_programming.longest_increasing_subsequence;

/**
 * 给定a[0] ... a[n-1]找到最长的上升子序列，不需要连续，输出最长序列的长度
 *
 * 输入：{4,2,4,5,3,7}
 * 输出：4（子序列2，4，5，7）
 *
 * 确定状态：
 * 对于最优策略，一定有最后一个元素a[j]
 *
 * f[j] = max{1, f[i] + 1} i < j a[i] < a[j]
 */
public class LongestIncreasingSubsequence {

    public static int longest(int[] a) {
        int n = a.length;
        if (n == 0) {
            return 0;
        }

        int[] f = new int[n];
        int result = 0;
        for (int j = 0; j < n; j++) {
            f[j] = 1;
            for (int i = 0; i < j; i++) {
                if (a[i] < a[j]) {
                    f[j] = Math.max(f[j], f[i] + 1);
                }
            }
            result = Math.max(result, f[j]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = {4,2,4,5,3,7};
        System.out.println(longest(a));
    }
}
