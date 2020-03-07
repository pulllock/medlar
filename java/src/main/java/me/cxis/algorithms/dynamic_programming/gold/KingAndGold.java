package me.cxis.algorithms.dynamic_programming.gold;

/**
 * 国王和金矿
 * 有一个国家发现5座金矿， 每座金矿的黄金储量不同，需要参与挖掘的工人数也不同。
 * 参与挖矿的工人总共有10人。每座金矿要么全挖，要么不挖，不能派出一般人挖一半金矿。
 * 要想得到尽可能多的黄金，应该选择挖取哪几座金矿？
 *
 * 金矿：400金/5人 500金/5人 200金/3人 300金/4人 350金/3人
 * 金矿 = n
 * 工人数 = w
 * 金矿含量为g[] = {400, 500, 200, 300, 350}
 * 金矿用人数p[] = {5, 5, 3, 4, 3}
 *
 * 问题应该是：给定金矿个数和人的个数，最多能挖取多少金子？（最值型动态规划）
 * 给定n个金矿和w个人，最多能挖取金子数量k
 *
 * 假设挖取最后一个金矿后能挖取到最多金子k1，那么问题就可以变成：
 * 给定n-1个金矿和w-p[n-1]个人，最多能挖取金子k1-g[n-1]
 *
 * 设f(n,w)=能挖取到最多金子k1
 * f(n,w)=k1
 * 则上面的假设可以得到下面的：
 * f(n-1,w-p[n-1]) = k1-g[n-1]
 *
 * f(n,w)=g[n-1] + f(n-1,w-p[n-1])
 *
 * 假设不挖取最后一个金矿就能挖到最多金子k2，那么问题就可以变成：
 * 给定n-1个金矿和w个人，最多能挖取金子k2
 * f(n-1,w)=k2
 *
 * 上面有两种假设得到的最多数量分别是k1和k2，我们最终的结果就是求：
 * max(k1,k2)
 *
 * f(n,w)=max(g[n-1] + f(n-1,w-p[n-1]), f(n-1,w))
 *
 * 以上就确定了转移方程，接下来需要确定初始值和边界问题
 *
 * 1. 金矿n=0，则f(n,w)=0
 * 2. 金矿n=1，则f(n,w)=第一个金矿的产量=g[0]，此时需要考虑实际有的人w是否够挖第一个金矿，所以可以分为两种情况：
 *      人数不够挖第一个金矿：金矿n=1，则f(n,w)=0,w<p[0]
 *      人数足够挖第一个金矿：金矿n=1，则f(n,w)=g[0],w>=p[0]
 * 3. 金矿n>1，根据第2条规则，也分两种情况：
 *      当人数不够挖第n座金矿：f(n,w)=f(n-1,w),w<p[n-1]
 *      当人数足够挖第n座金矿：f(n,w)=max(f(n-1, w), f(n-1, w-p[n-1])+g[n-1)
 *
 * 可以总结如下：
 * n<=1 w<p[0] f(n, w)=0
 * n==1 w>=p[0] f(n, w)=g[0]
 * n>1 w<p[n-1] f(n, w)=f(n-1, w)
 * n>1 w>=p[n-1] f(n, w)=max(f(n-1, w), f(n-1, w-p[n-1])+g[n-1])
 *
 * w个人挖n个金矿，能挖到最多黄金，则
 */
public class KingAndGold {

    /**
     *
     * @param n 金矿数
     * @param w 矿工数
     * @param g 金子含量
     * @param p 使用的矿工数
     * @return
     */
    public int getMostGold(int n, int w, int[] g, int[] p) {
        // 用来存放第n个金矿w个人，能挖取的最大金子数量
        int[][] f = new int[n + 1][w + 1];

        for (int i = 0; i <= w; i++) {
            f[0][i] = 0;
        }


        // 金矿
        for (int i = 1; i <= n; i++) {
            // 矿工
            for (int j = 1; j <= w; j++) {
                f[i][j] = Integer.MIN_VALUE;
                // 矿工人数比第i个金矿所需的人数少
                if (j < p[i - 1]) {
                    f[i][j] = f[i - 1][j];
                } else {
                    f[i][j] = Math.max(f[i - 1][j], g[i - 1] + f[i - 1][j - p[i - 1]]);
                }
            }
        }
        return f[n][w];
    }

    public int getMostGold1(int w, int[][] a) {
        return 0;
    }

    public int getMostGold2(int n, int w, int[] g, int[] p) {
        int[] preResults = new int[w + 1];
        int[] results = new int[w + 1];

        for (int i = 0; i <= w; i++) {
            if (i < p[0]) {
                preResults[i] = 0;
            } else {
                preResults[i] = g[0];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < w + 1; j++) {
                if (j < p[i]) {
                    results[j] = preResults[j];
                } else {
                    results[j] = Math.max(preResults[j], preResults[j - p[i]] + g[i]);
                }
            }
            for(int j = 0; j < w + 1; j++) {
                preResults[j] = results[j];
            }

        }
        return results[w];
    }

        public static void main(String[] args) {
        KingAndGold gold = new KingAndGold();
        int n = 5;
        int w = 10;
        int[] g = {400, 500, 200, 300, 350};
        int[] p = {5, 5, 3, 4, 3};
        System.out.println(gold.getMostGold(n, w, g, p));
        System.out.println(gold.getMostGold2(n, w, g, p));
    }
}
