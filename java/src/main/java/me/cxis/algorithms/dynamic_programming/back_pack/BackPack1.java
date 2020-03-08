package me.cxis.algorithms.dynamic_programming.back_pack;

/**
 * 给定n个物品，重量分别是A0 A1 ... An-1，
 * 一个背包最大承重是M，
 * 问最多能带走多重的物品。
 *
 * 输入：4个物品 重量为2，3，5，7 背包最大承重11
 * 输出：10（三个物品 2，3，5）
 *
 * 确定状态：
 * 需要知道N个物品是否能拼出重量W（W=0，1，...，M）
 * 最后一步：最后一个物品（重量An-1）是否进入背包
 * 情况1：如果前N-1个物品能拼出W，则前N个物品也能拼出W
 * 情况2：如果前N-1个物品能拼出W-An-1，再加上最后的物品An-1，拼出W
 *
 * 求前N个物品能不能拼出重量0，1，...M
 * 需要知道前N-1个物品能不能拼出重量0，1，...M
 *
 * 状态：设f[i][w]=能否用前i个物品拼出重量w
 *
 * f[i][w] = f[i-1][w] OR f[i-1][w-Ai-1]
 *
 * 初始条件
 * f[0][0]=true
 * f[0][1...M]=false
 *
 * w>=Ai-1
 */
public class BackPack1 {

    public static int backPack(int m, int[] a) {
        int n = a.length;
        if (n == 0) {
            return 0;
        }

        boolean[][] f = new boolean[n + 1][m + 1];
        f[0][0] = true;
        for (int i = 1; i <= m; i++) {
            f[0][i] = false;
        }

        // 物品
        for (int i = 1; i <= n; i++) {
            // 背包承重
            for (int j = 0; j <= m; j++) {
                f[i][j] = f[i-1][j];
                if (j >= a[i-1]) {
                    f[i][j] |= f[i-1][j-a[i-1]];
                }
            }
        }

        int result = 0;
        for (int i = m; i >=0; i--) {
            if (f[n][i]) {
                result = i;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int m = 11;
        int[] a = {2,3,5,7};
        System.out.println(backPack(m, a));
    }
}
