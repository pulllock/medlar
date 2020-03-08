package me.cxis.algorithms.dynamic_programming.palindrome_partitioning;

/**
 * 给定一个字符串s[0...N-1]
 * 要求将这个字符串划分成若干段，每一段都是一个回文串，
 * 求最少划分几次
 *
 * 输入：aab
 * 输出：1（划分一次aa，b）
 *
 * 确定状态：
 * 最后一步：最优策略中最后一段回文串设为s[j...n-1]
 * 需要知道s前j个字符[0...j-1]最少可以划分为几个回文串
 *
 * 状态：设s前i个字符s[0...i-1]最少可以划分为f[i]个回文串
 *
 * f[i] = min{f[j] + 1} j=0...i-1 s[j...i-1]是回文串
 *
 * 初始化
 * f[0] = 0
 *
 * 回文串分两种：
 * 长度为奇数 abcdcba
 * 长度为偶数 abcddcba
 *
 * 在字符串中找到所有回文串：
 * 以字符串的每一个字符为中点，向两边扩展，找到所有回文串
 *
 *
 */
public class PalindromePartitioning {

    public static int minCut(String ss) {
        char[] s = ss.toCharArray();
        int n = s.length;
        if (n == 0) {
            return 0;
        }

        boolean[][] isPalin = calcPalin(s);
        int[] f = new int[n + 1];
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            f[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (isPalin[j][i - 1]) {
                    f[i] = Math.min(f[i], f[j] + 1);
                }
            }
        }
        return f[n] - 1;
    }

    private static boolean[][] calcPalin(char[] s) {
        int n = s.length;
        boolean[][] f = new boolean[n][n];

        int i, j, c;
        for (i = 0; i < n; i++) {
           for (j = i; j < n; j++) {
               f[i][j] = false;
           }
        }
        // 奇数
        for (c = 0; c < n; c++) {
            i = j = c;
            while (i >= 0 && j < n && s[i] == s[j]) {
                f[i][j] = true;
                i--;
                j++;
            }
        }

        // 偶数
        for (c = 0; c < n - 1; c++) {
            i = c;
            j = c + 1;
            while (i >= 0 && j < n && s[i] == s[j]) {
                f[i][j] = true;
                i--;
                j++;
            }
        }
        return f;
    }

    public static void main(String[] args) {
        String ss = "aab ";
        System.out.println(minCut(ss));
    }
}
