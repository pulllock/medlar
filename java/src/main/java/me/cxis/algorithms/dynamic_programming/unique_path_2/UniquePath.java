package me.cxis.algorithms.dynamic_programming.unique_path_2;

/**
 * 给定m行n列的网格，有一个机器人从左上角(0,0)出发，每一步可向下或者向右走一步，
 * 网格中有些地方有障碍，机器人不能通过障碍格子，问有多少种不同的方式走到右下角
 *
 * 确定状态：
 * 最后一步：一定是从(i, j-1)或者(i-1,j)走过来
 *
 * 设f[i][j]=从左上角有多少种方式走到格子(i,j)
 *
 * f[i][j] = f[i][j-1] + f[i-1][j]
 *
 * 坐标型动态规划
 *
 * 初始值和边界情况：
 * 如果左上角(0,0)或者右下角(m-1,n-1)有障碍，结果直接为0
 *
 * 如果(i,j)有障碍，说明机器人不能到达该格子，所以f[i][j]=0
 *
 * f[0][0]=1
 */
public class UniquePath {

    public int uniquePath(int[][] a) {
        int m = a.length;
        if (m ==0 ) {
            return 0;
        }

        int n = a[0].length;
        if (n == 0) {
            return 0;
        }

        int[][] f = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 有障碍
                if (a[i][j] == 1) {
                    f[i][j] = 0;
                    continue;
                }
                // 初始值
                if (i == 0 && j == 0) {
                    f[i][j] = 1;
                    continue;
                }

                /*f[i][j] = 0;
                if (i > 0) {
                    f[i][j] += f[i -1][j];
                }

                if (j > 0) {
                    f[i][j] += f[i][j - 1];
                }*/
                if (i == 0) {
                    f[i][j] = f[i][j - 1];
                    continue;
                }

                if (j == 0) {
                    f[i][j] = f[i - 1][j];
                    continue;
                }

                f[i][j] = f[i][j-1] + f[i-1][j];
            }
        }
        return f[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] a = {{0,0,0},{0,1,0},{0,0,0}};
        UniquePath uniquePath = new UniquePath();
        System.out.println(uniquePath.uniquePath(a));
    }
}
