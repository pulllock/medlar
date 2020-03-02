package me.cxis.algorithms.dynamic_programming.decode_ways;

/**
 * 有一段由A-Z组成的字母串被加密成数字串
 * 加密方式为A-1 B-2 ... Z-26
 * 给定加密后的数字串s[0...n-1] 问有多少种方式解密成字母串
 *
 * 划分型动态规划
 *
 * 输入12
 * 输出2（AB或L）
 *
 * 确定状态：
 * 解密数字串即划分成若干段数字，每段数字对应一个字母
 *
 * 最后一步（最后一段）：对应一个字母 A B ...Z
 *
 * 设数字串长度为N，要求数字串前N个字符的解密方式数，
 * 需要知道数字串前N-1和前N-2个字符的解密方式数
 *
 * 子问题：需要知道数字串前N-1和前N-2个字符的解密方式数
 * 状态：设数字串s前i个数字解密成字母串有f[i]种方式
 *
 * 状态转移方程：
 * f[i]=f[i-1]+f[i-2]
 *
 * 初始值和边界：
 * f[0]=1
 *
 * 边界：如果i=1 只看最后一个数字
 *
 * 计算顺序 f[0] f[1] ... f[n]
 * 结果：f[n]
 * 时间和空间复杂度O(N)
 *
 */
public class DecodeWays {

    public int decodeWays(String ss) {
        char[] s = ss.toCharArray();
        int n = s.length;

        if (n == 0) {
            return 0;
        }

        int[] f = new int[n + 1];
        f[0] = 1;

        int j;
        for (int i = 1; i <= n; i++) {
            f[i] = 0;
            if (s[i - 1] >= '1' && s[i - 1] <= '9') {
                f[i] = f[i] + f[i - 1];
            }

            if (i > 1) {
                // s[i-2][i-1]
                j = 10 * (s[n -2] - '0') + (s[i - 1] - '0');
                if (s[i - 2] != '0' && j >= 10 && j <= 26) {
                    f[i] += f[i -2];
                }
            }
        }
        return f[n];
    }

    public static void main(String[] args) {
        String ss = "12";
        DecodeWays decodeWays = new DecodeWays();
        System.out.println(decodeWays.decodeWays(ss));
    }
}
