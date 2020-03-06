package me.cxis.algorithms.dynamic_programming.back_pack;

/**
 * 有N件物品和一个容量为V的背包，第i件物品的重量是c[i]，价值是w[i]，
 * 求将哪些物品放入背包，可以使这些物品的总重量不超过背包容量，并且价值最大。
 *
 * 假如可以放入i件物品，使得容量V的背包中物品价值总和最大
 * 对于第i件物品可以选择放或者不放，
 * 如果选择不放，就是要考虑i-1件物品，使得容量V背包中物品价值总和最大，
 * 如果选择放，就是要考虑i-1件物品，使得容量为V-c[i]背包中物品价值总和最大
 *
 * 最后结果就是要求放和不放的最大值
 *
 * 设f[i][v]表示放入i件物品，容量v背包的最大价值
 * 则：f[i][v] = max{f[i-1][v], f[i-1][v-c[i]] + w[i]}
 *
 * 0件物品 f[0][v] = 0;
 * 1件物品 f[1][v] = 0 ,c[1] > v
 * 1件物品 f[1][v] = w[1] , c[1] <= v
 * i件物品 f[i][v] = f[i-1][v] c[i] > v
 * i件物品 f[i][v] = max{f[i-1][v], f[i-1][v-c[i]] + w[i]}  c[i] <= v
 */
public class BackPack {

    public static int backPack(int v, int[] c, int[] w) {
        // 物品数量
        int n = c.length;

        // 表示第i件物品放在容量为v的背包里的最大价值
        int[][] f = new int[n + 1][v + 1];


        for (int i = 0; i <= v; i++) {
            f[0][i] = 0;
        }

        // TODO
        // n件物品
        for (int i = 1; i < n; i++) {

            // 容量
            for (int j = 0; j <= v; j++) {
                f[i][j] = Integer.MIN_VALUE;

                if (c[i] > j) {
                    f[i][j] = f[i-1][j];
                } else {
                    f[i][j] = Math.max(f[i-1][j], f[i-1][v-c[i]] + w[i]);
                }
            }
        }

        return f[n][v];
    }

    public static void main(String[] args) {
        // 背包容量
        int v = 120;
        // 重量
        int[] c = {10, 25, 40, 20, 10};
        // 价值
        int[] w = {40, 50, 70, 40, 20};

        System.out.println(backPack(v, c, w));
    }
}
