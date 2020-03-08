package me.cxis.algorithms.dynamic_programming.house_robber;

/**
 * 有一排N栋房子（0 ~ N-1）,房子i中有A[i]个金币，有一个窃贼想选择一些房子里的金币偷取，
 * 不能偷取相邻房子里的金币，最多能偷取多少金币？
 *
 * 输入：A={3, 8 ,4}
 * 输出：8
 *
 * 确定状态：
 * 在窃贼的最优策略中，有可能偷或者不偷最后一栋房子N-1：
 * 如果不偷最后一栋房子N-1，则最优策略就是前N-1栋房子的最优策略
 * 如果偷取最后一栋房子N-1，则最优策略还是前N-1栋房子的最优策略，但需要保证不偷N-2栋房子
 *
 * 如何保证在不偷第N-2栋房子的前提下，在前N-1栋房子中能够偷取最多的金币？
 *
 * f[i][0]表示不偷第i-1栋房子的前提下，前i栋房子能偷取的最多的金币。
 * f[i][1]表示偷取第i-1栋房子的前提下，前i栋房子能偷取的最多的金币
 *
 * f[i][0] = max{f[i-1][0], f[i-1][1]} 因为不偷第i-1房子，所以第i-2房子可以选择偷或者不偷
 *
 * f[i][1] = f[i-1][0] + A[i-1] 因为偷第i-1房子，所以第i-2房子必须不能偷
 *
 * 简化：在不偷房子i-1的前提下，前i栋房子中最多能偷多少金币，
 * 其实就是前i-1栋房子最多能偷取多少金币
 *
 * 设f[i]表示在前i栋房子最多能偷多少金币
 * f[i] = max{f[i-1], f[i-2] + A[i-1]}
 *
 * 初始条件和边界：
 * f[0]=0
 * f[1]=A[0]
 * f[2]=max{A[0],A[1]}
 */
public class HouseRobber {

    public static int houseRobber(int[] a) {
        int n = a.length;

        int[] f = new int[n + 1];
        f[0] = 0;
        f[1] = a[0];
        f[2] = Math.max(a[0], a[1]);
        for (int i = 3; i <= n; i++) {
            f[i] = Math.max(f[i-1], f[i-2] + a[i-1]);
        }
        return f[n];
    }

    public static int houseRobber1(int[] a) {
        int n = a.length;

        int old = 0;
        int now = a[0];
        for (int i = 2; i <= n; i++) {
            now = Math.max(now, old + a[i-1]);
        }
        return now;
    }

    public static void main(String[] args) {
        int[] a = {3, 8, 4};
        System.out.println(houseRobber(a));
        System.out.println(houseRobber1(a));
    }
}
