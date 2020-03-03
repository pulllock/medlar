package me.cxis.algorithms.dynamic_programming.longest_subsequence;

/**
 * 给定a[0] a[1] ... a[n-1],找到最长的连续子序列 i i+1 i+2 ... j
 * 使得a[i] < a[i+1]...<a[j]或者a[i]  > a[i+1]...>a[j]输出长度j-i+1
 *
 * 输入：[5,1,2,3,4]
 * 输出：4（子序列1234）
 *
 * 确定状态：
 * 最后一步：对于最优策略，一定有最后一个元素a[j]
 *
 * 如果最长连续子序列长度是1，结果就是1
 * 如果最长连续子序列长度大于1，最优策略中a[j]前面一个就是a[j-1] 且a[j-1]<a[j]
 *
 * 以a[j-1]结尾的连续子序列也一定是最长的
 *
 * 子问题：要求以a[j-1]结尾的最长连续上升子序列
 * 原问题：要求以a[j]结尾的最长连续上升子序列
 *
 * 状态：设f[j]=以a[j]结尾的最长连续上升子序列的长度
 *
 * 状态转移方程：f[j] = max{1, f[j-1]+1} j>0 and a[j-1]<a[j]
 *
 * 初始值和边界：
 * 初始条件：空或f[0]=1
 * 边界：j>0 and a[j-1]<a[j]
 *
 * 计算顺序：f[0] f[1] ... f[n-1]
 * 结果：max{f[0]...f[n-1]}
 * 时间和空间复杂度O(n)
 */
public class LongestSubsequence {

    public static int longest(int[] a) {
        int n = a.length;

        if (n == 0) {
            return 0;
        }

        // 求最长连续上升子序列
        int r1 = longest(a, n);

        // 将原数组倒过来，再求一次最长连续上升子序列，其实就是原数组的最长连续递减子序列
        int i = 0;
        int j = n - 1;
        int temp;
        while (i < j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i++;
            j--;
        }

        int r2 = longest(a, n);
        return Math.max(r1, r2);

    }

    private static int longest(int[] a, int n) {
        int[] f = new int[n];

        int result = 0;
        for (int i = 0; i < n; i++) {
            f[i] = 1;
            if (i > 0 && a[i - 1] < a[i]) {
                f[i] = f[i - 1] + 1;
            }
            result = Math.max(result, f[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = {5,1,2,3,4};
        System.out.println(longest(a));
    }
}
