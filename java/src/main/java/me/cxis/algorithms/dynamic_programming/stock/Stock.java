package me.cxis.algorithms.dynamic_programming.stock;

/**
 * 已知后面N天一只股票每天的价格P0 P1 P2 .. PN-1，可以最多买一股卖一股，求最大利润
 *
 * 输入：[3,2,3,1,2]
 * 输出：1 买2卖3
 *
 * 要盈利，需要买低价，卖高价，并且买在卖前面，
 * 第i天买股票，第j天卖股票，i < j, pi > pj, 收益是：pj - pi,
 * 所以我们需要找找第几天卖价格最高，第几天买价格最低，i < j
 *
 * 需要枚举从0到n-1找到j，也就是找到第几天卖
 * 然后需要保存当前为止，0到j-1天的最低价格pi
 * 最大的pj 减去pi就是答案
 *
 *
 */
public class Stock {

    public static int stock(int[] a) {
        int n = a.length;
        if (n == 0) {
            return 0;
        }

        int result = 0;
        // 到当前为止的最小值
        int minV = a[0];

        for (int i = 1; i < n; i++) {
            result = Math.max(result, a[i] - minV);
            minV = Math.min(minV, a[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] a = {3,2,3,1,2};
        System.out.println(stock(a));
    }
}
