package me.cxis.algorithms.dynamic_programming.paint_house;

/**
 * 有一排N栋房子，每栋房子要漆成3种颜色中的一种：红、蓝、绿，任何两栋相邻的房子不能漆成同样的颜色，
 * 第i栋房子染成红色 蓝色 绿色的价格分别是cost[i][0] cost[i][1] cost[i][2]
 * 问最少需要多少钱来油漆这些房子
 *
 * 序列型动态规划
 *
 * 输入N=3 cost=[[14,2,11],[11,14,5],[14,3,10]]
 * 输出10 第0栋房子蓝色 第1栋房子绿色 第2栋房子蓝色 2+5+3=10
 *
 * 确定状态：
 * 最优策略是花费最小的策略
 *
 * 最后一步：最优策略中房子n-1一定染了红蓝绿中的一种
 * 相邻两栋房子不能是同一种颜色，所以：
 * 如果最优策略中n-1是红色，n-2只能是蓝色或绿色
 * 如果最优策略中n-1是蓝色，n-2只能是红色或绿色
 * 如果最优策略中n-1是绿色，n-2只能是红色或蓝色
 *
 * 问题：油漆前n栋房子的最小花费
 * 子问题：油漆前n-1栋房子的最小花费
 * 同时还需要记录n-2是红色、蓝色、绿色的最小花费
 *
 * 问题变成：求漆前n栋房子并且n-1是红色、蓝色、绿色的最小花费
 * 子问题：求漆前n-1栋房子并且n-2是红色、蓝色、绿色的最小花费
 *
 * 状态：
 * 设油漆前i栋房子并且房子i-1是红色、蓝色、绿色的最小花费分别是：f[i][0] f[i][1] f[i][2]
 *
 * 状态转移方程：
 * f[i][0] = min{f[i-1][1] + cost[i-1][0], f[i-1][2] + cost[i-1][0]}
 * f[i][1] = min{f[i-1][0] + cost[i-1][1], f[i-1][2] + cost[i-1][1]}
 * f[i][2] = min{f[i-1][0] + cost[i-1][2], f[i-1][1] + cost[i-1][2]}
 *
 * 初始条件和边界
 * f[0][0]=f[0][1]=f[0][2]=0 不油漆任何房子的花费
 *
 * 结果：min{f[n][0], f[n][1], f[n][2]}
 * 时间复杂度O(N) 空间复杂度O(N)
 */
public class PaintHouse {

    public int minCost(int[][] c) {
        // 房子数
        int n = c.length;

        if (n == 0) {
            return 0;
        }

        int[][] f = new int[n + 1][3];

        f[0][0] = 0;
        f[0][1] = 0;
        f[0][2] = 0;

        // n栋房子
        for (int i = 1; i <= n; i++) {
            // n-1栋房子颜色
            for (int j = 0; j < 3; j++) {
                f[i][j] = Integer.MAX_VALUE;
                // n-2栋房子颜色
                for (int k = 0; k < 3; k++) {
                    if (k != j) {
                        f[i][j] = Math.min(f[i][j], f[i-1][k] + c[i-1][j]);
                    }
                }
            }
        }
        return Math.min(Math.min(f[n][0], f[n][1]), f[n][2]);
    }

    public static void main(String[] args) {
        int[][] c = {{14,2,11},{11,14,5},{14,3,10}};

        PaintHouse paintHouse = new PaintHouse();
        System.out.println(paintHouse.minCost(c));
    }
}
