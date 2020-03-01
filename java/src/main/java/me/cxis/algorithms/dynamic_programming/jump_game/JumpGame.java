package me.cxis.algorithms.dynamic_programming.jump_game;

/**
 * 有n块石头分别在x轴的0,1,2,...,n-1的位置，一只青蛙在0的位置，想跳到第n-1块石头上，
 * 如果青蛙在第i块石头上，最多可以向右跳的距离是ai，
 * 问青蛙能否跳到石头n-1上
 *
 * a=[2,3,1,1,4] true
 * a=[3,2,1,0,4] false
 *
 * 存在型动态规划
 * 问题：青蛙能否跳到石头n-1上
 *
 * 确定状态：
 * 如果青蛙能跳到最后一块石头n-1上，最后一步跳的时候青蛙在石头i上，i<n-1，那么:n-1 <= a[i] + i
 * 我们需要确定：青蛙是否能跳到石头i上
 *
 * 子问题：青蛙能否跳到石头i上
 *
 * 设f(x)=青蛙能否跳到石头x上, 0<x<n-1, x+a[x]>=n-1
 *
 * 状态转移方程：
 * f[j]=OR(f(i) AND i + a[i] >=j) 0<=i<j
 *
 * 确定初始值和边界：
 * f(0)=true
 *
 * 计算顺序：
 * f[1] f[2]... f[n-1]
 */
public class JumpGame {

    public boolean jumpGame(int[] a) {
        // 有多少块石头
        int n = a.length;
        boolean[] f = new boolean[n];

        f[0] = true;
        // 分别计算能否跳到j
        for (int j = 1; j < n; j++) {
            f[j] = false;
            // 分别计算j前面的能否跳到j
            for (int i = 0; i < j; i++) {
                // 能跳到i并且能跳到j
                if (f[i] && i + a[i] >= j) {
                    f[j] = true;
                }
            }
        }
        return f[n - 1];
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 1, 1, 4};
        int[] b = {3, 2, 1, 0, 4};
        JumpGame jumpGame = new JumpGame();
        System.out.println(jumpGame.jumpGame(a));
        System.out.println(jumpGame.jumpGame(b));
    }
}
