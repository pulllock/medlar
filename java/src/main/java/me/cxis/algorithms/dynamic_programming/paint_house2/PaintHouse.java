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
 *
 * 优化：
 * 记录下f[i-1][1]...f[i-1][k]中的最小值和次小值分别是哪个
 *
 * 假如最小值是f[i-1][a]次小值是f[i-1][b]
 * 对于j=1,2,...a-1,a+1...k f[i][j] = f[i-1][a]+coast[i-1][j]
 *
 * f[i][a]=f[i-1][b]+coast[i-1][a]
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

    public static int paintHouse2(int[][] coast) {
        // 房子数
        int n = coast.length;

        // 颜色数
        int c = coast[0].length;

        int[][] f = new int[n + 1][c];

        // 最小值
        int min1;
        // 次小值
        int min2;

        // 第几个是最小值
        int id1 = 0;
        int id2 = 0;

        // 初始化，0栋房子花费0
        for (int i = 0; i < c; i++) {
            f[0][i] = 0;
        }

        // n栋房子
        for (int i = 1; i <= n; i++) {
            min1 = min2 = Integer.MAX_VALUE;
            for (int j = 0; j < c; j++) {
                // 比最小值小，最小值变成了次小值
                if (f[i - 1][j] < min1) {
                    min2 = min1;
                    id2 = id1;
                    min1 = f[i-1][j];
                    id1 = j;
                } else {
                    // 比次小值小，比最小值大，则该值变成了次小值
                    if (f[i-1][j]<min2) {
                        min2 = f[i-1][j];
                        id2 = j;
                    }
                }
            }

            for (int j = 0; j < c; j++) {
                f[i][j] = coast[i-1][j];
                // 不是最小的
                if (j != id1) {
                    f[i][j] += min1;
                } else {
                    // 是最小值
                    f[i][j] += min2;
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < c; i++) {
            res = Math.min(res, f[n][i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] c = {{14,2,11},{11,14,5},{14,3,10}};
        System.out.println(paintHouse(c));
        System.out.println(paintHouse2(c));
    }
}
