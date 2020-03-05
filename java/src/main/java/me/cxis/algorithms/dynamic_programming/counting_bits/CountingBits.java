package me.cxis.algorithms.dynamic_programming.counting_bits;

import java.util.Arrays;

/**
 * 给定N，输出0,1,2....,N的每个数的二进制表示中含有1的个数
 *
 * 输入：5
 * 输出：[0,1,1,2,1,2]
 *
 * 0:0
 * 1:1
 * 2:10
 * 3:11
 * 4:100
 * 5:101
 *
 *  要求N的二进制表示中中有多少个1，在N的二进制表示去掉最后一位 N % 2 ,设新的数是Y = (N>>1)，
 *  需要求出Y的二进制表示中有多少个1
 *
 *  子问题：求Y的二进制表示中有多少个1
 *
 *  状态：设f[i]表示i的二进制表示中有多少个1
 *
 *  f[i] = f[i>>1] + i % 2
 *
 *  初始条件：f[0] = 0
 *
 *  计算顺序：f[0] f[1] ... f[n]
 *  时间空间复杂度O(N)
 */
public class CountingBits {

    public static Integer[] countingBits(int n) {
        Integer[] f = new Integer[n + 1];
        f[0] = 0;
        for (int i = 0; i <= n; i++) {
            if (i > 0) {
                f[i] = f[i >> 1] + (i % 2);
            }
        }
        return f;
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println(Arrays.asList(countingBits(n)));
    }
}
