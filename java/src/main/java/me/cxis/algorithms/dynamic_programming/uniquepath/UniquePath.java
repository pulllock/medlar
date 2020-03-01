package me.cxis.algorithms.dynamic_programming.uniquepath;

/**
 * 给定m行n列网格，有一个机器人从左上角(0,0)出发，每一步可向下或向右走一步，问有多少种不同的方式走到右下角。
 *
 * 计数型动态规划
 *
 * 问题是：有多少种不同方式走到格子(m-1,n-1)，右下角的坐标是(m-1,n-1)
 *
 * 先确定状态：最后一步和子问题
 *
 * 最后一步是走到最右下角(m-1,n-1)，只可能有两种走法：从上面一格(m-2,n-1)走、从左面一格走(m-1,n-2)
 * 假设走到(m-2,n-1)格需要x步，走到(m-1,n-2)格需要y步，那么走到(m-1,n-1)格就需要x+y步
 *
 * 子问题：有多少种不同方式走到格子(m-2,n-1)和(m-1,n-2)
 *
 * 设f(m,n)=有多种不同方式走到格子(m,n)
 *
 * f(m,n)=f(m-1,n) + f(m,n-1)
 *
 * 初始化和边界
 * f(0,0)=1
 * f(0,1)=1,f(0,2)=1...f(0,n-1)=1
 * f(1,0)=1,f(2,0)=1...f(m-1,0)=1
 *
 * 状态转移方程
 * f[m][n]=f[m-1][n] + f[m][n-1]
 *
 */
public class UniquePath {

    public int uniquePath(int m, int n) {
        // 存储第m行n列有多少种走法
        int[][] f = new int[m][n];

        // 逐行开始走
        for (int i = 0; i < m; i++) {

            // 逐列开始走
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    f[i][j] = 1;
                    continue;
                }
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int m = 4;
        int n = 5;
        UniquePath uniquePath = new UniquePath();
        System.out.println(uniquePath.uniquePath(m, n));
    }
}
