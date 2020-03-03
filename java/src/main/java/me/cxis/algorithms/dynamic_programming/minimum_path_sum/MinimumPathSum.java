package me.cxis.algorithms.dynamic_programming.minimum_path_sum;

/**
 * 给定m行n列网格，每个格子(i,j)里都有一个非负数a[i][j]，求一个从左上角(0,0)到右下角的路径，
 * 每一步只能向下或者向右走一步，使得路径上的格子里的数字之和最小，输出最小数字和。
 *
 * 确定状态：
 * 最后一步：到达右下角(m-1,n-1)的时候是从上面或者左边走来，到达右下角的前一步是：(m-2,n-1)或者(m-1,n-2)
 * 最优策略：路径总和数字最小
 * 如果倒数第二步在(m-2,n-1)，则前面一定是从(0,0)到达(m-2,n-1)总和最小的路径
 * 如果倒数第二步在(m-1,n-2)，则前面一定是从(0,0)到达(m-1,n-2)总和最小的路径
 *
 * 子问题：求从左上角走到(m-2,n-1)的路径最小总和以及走到(m-1,n-2)的路径最小总和
 * 原问题：求从左上角走到(m-1,n-1)的路径最小总和
 *
 * 状态：设f[i][j]=从(0,0)走到(i,j)的路径最小数字总和
 *
 * 状态转移方程：f[i][j]=min(f[i-1][j], f[i][j-1])+a[i][j]
 *
 * 初始值和边界：
 * f[0][0]=a[0][0]
 * i=0或j=0
 *
 * 计算顺序：
 * f[0][0] ... f[0][j]
 *
 * 时间和空间复杂度：O(MN)
 */
public class MinimumPathSum {

     public static int minPathSum(int[][] a) {
         int m = a.length;
         if (m == 0) {
             return 0;
         }

         int n = a[0].length;
         if (n == 0) {
             return 0;
         }

         int[][] f = new int[m][n];

         for (int i = 0; i < m; i++) {
             for (int j = 0; j < n; j++) {
                 if (i == 0 && j == 0) {
                     f[i][j]= a[i][j];
                     continue;
                 }
                 f[i][j] = Integer.MAX_VALUE;
                 if (i > 0) {
                     f[i][j] = Math.min(f[i-1][j], f[i][j]);
                 }

                 if (j > 0) {
                     f[i][j] = Math.min(f[i][j], f[i][j-1]);
                 }
                 f[i][j] += a[i][j];

                 /*int t = Integer.MAX_VALUE;
                 if (i > 0) {
                     t = Math.min(t, f[i-1][j]);
                 }

                 if (j > 0) {
                     t = Math.min(t, f[i][j-1]);
                 }
                 f[i][j] = t + a[i][j];*/
             }
         }
         return f[m-1][n-1];
     }

    public static void main(String[] args) {
        int[][] a = {
                {1,5,7,6,8},
                {4,7,4,4,9},
                {10,3,2,3,2}
        };

        System.out.println(minPathSum(a));
    }
}
