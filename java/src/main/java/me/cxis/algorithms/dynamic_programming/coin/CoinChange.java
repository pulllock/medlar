package me.cxis.algorithms.dynamic_programming.coin;

import java.util.Arrays;

/**
 * 有三种硬币，面值分别为2元 5元 7元，硬币足够多，使用这些硬币组合凑出27元，最少需要多少个硬币？
 *
 * 问题：最少用多少硬币凑出27
 *
 * 假设最少存在k个硬币，可以凑出27， 最后一个硬币是ak，面值确定，那么前面k-1个硬币的和就是27-ak
 * 凑出27-ak元使用k-1个硬币，k-1个硬币必须也是最少的个数
 * 问题就变成了：最少用多少硬币凑出27-ak
 *
 * 设f(x)=最少用多少硬币凑出x
 *
 * 硬币的面额是2、5、7 所以最后一枚硬币取值范围是：2、5、7
 * 如果ak=2，f(27)=1+f(27-2)
 * 如果ak=5，f(27)=1+f(27-5)
 * 如果ak=7，f(27)=1+f(27-7)
 * ak确定的情况下只可能有上面三种情况，所以只需要知道三种情况中的最小值即可：
 * f(27) = min{1+f(27-2), 1+f(27-5), 1+f(27-7)}
 *
 * 转移方程：f(x) = min{f(x-2)+1, f(x-5)+1, f(x-7)+1}
 *
 * 接下来需要确认初始值和边界
 *
 * f(0)=0
 *
 * 硬币面值：a[] = {2, 5, 7}
 * 凑出结果：m=27
 *
 */
public class CoinChange {

    public int coinChange(int m, int[] a) {
        // 存放凑出来每一个钱数所需要的最少硬币数，比如：凑出来0需要多少个，凑出来1需要多少个
        Integer[] f = new Integer[m + 1];
        // 凑出0元需要0个硬币
        f[0] = 0;

        // 需要凑出的钱数：1, ..., 27
        for (int i = 1; i <= m; i++) {
            f[i] = Integer.MAX_VALUE;

            // 硬币面值：2, 5, 7
            for (int j = 0; j < a.length; j++) {
                if (i - a[j] >= 0 && f[i - a[j]] != Integer.MAX_VALUE) {
                    f[i] = Math.min(f[i], f[i - a[j]] + 1);
                }
            }
            System.out.println(Arrays.asList(f));
        }
        return f[m] == Integer.MAX_VALUE ? -1 : f[m];
    }

    public static void main(String[] args) {
        int m = 27;
        int[] a = {2, 5, 7};
        CoinChange coinChange = new CoinChange();
        System.out.println(coinChange.coinChange(m, a));
    }
}
