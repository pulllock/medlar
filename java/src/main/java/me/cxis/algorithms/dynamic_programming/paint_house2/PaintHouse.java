package me.cxis.algorithms.dynamic_programming.paint_house2;

/**
 * 有一排N栋房子，每栋房子要漆成K种颜色中的一种，相邻两栋房子不能是同一种颜色，房子i染成第j种颜色的花费是cost[i][j]
 * 问最少需要花多少钱油漆这些房子
 *
 * 输入：N=3 K=3
 * cost = [[14,2,11],[11,14,5],[14,3,10]]
 * 输出：10
 *
 * 设油漆前i栋房子，并且i-1栋房子的颜色是1,2,3...k的最小花费是f[i][1] f[i][2] f[i][3]...f[i][k]
 *
 * f[i][1] = min{f[i-1][2] + cost[i-1][1], f[i-1][3] + cost[i-1][1], ..., f[i-1][k] + cost[i-1][1]}
 * ...
 * f[i][k] = min{f[i-1][1] + cost[i-1][k], ..., f[i-1][k-1] + cost[i-1][k]}
 *
 * 状态转移方程：
 * 设油漆前i栋房子并且i-1栋房子的颜色是1 2 ... k的最小花费分别是f[i][1]...f[i][k]
 * f[i][j] = min{f[i-1][k]} + cost[i-1][j] k!=j
 */
public class PaintHouse {


    public static int paintHouse(int[][] cost) {
        // 房子数
        int n = cost.length;
        // 颜色数
        int c = cost[0].length;

        int[][] f = new int[n + 1][c];
        for (int i = 0; i < c; i++) {
            f[0][i] = 0;
        }
        // n栋房子
        for (int i = 1; i <= n; i++) {

            // n-1栋房子颜色
            for (int j = 0; j < c; j++) {
                f[i][j] = Integer.MAX_VALUE;
                // n-2栋房子颜色
                for (int k = 0; k < c; k++) {
                    if (i > 0 && k != j) {
                        f[i][j] = Math.min(f[i][j], f[i-1][k] + cost[i-1][j]);
                    }
                }

            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < c; i++) {
            result = Math.min(result, f[n][i]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] c = {{14,2,11},{11,14,5},{14,3,10}};
        System.out.println(paintHouse(c));
    }
}
