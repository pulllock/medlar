package me.cxis.algorithms.dynamic_programming.triangle;

import java.util.HashMap;
import java.util.Map;

/**
 * 给出一个三角形（数据数组），找出从上往下的最小路径和。每一步只能移动到下一行中的相邻结点上。
 *
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 *  则从上至下最小路径和为11（即，2 + 3 + 5 + 1 = 11）
 *
 *  三角形可用二维数组表示 m[][] = {
 *             {2, 0, 0, 0},
 *             {3, 4, 0, 0},
 *             {6, 5, 7, 0},
 *             {4, 1, 8, 3}
 *  }
 *
 *  求最上面结点到最下面结点的最小路径和
 *  最后一步是：求最下面结点的最小路径和，就是最下面结点本身
 *  求每个节点到最下面结点的最小路径和
 *
 *  设f[i][j]=到最下面结点的最小路径和
 *  f[i][j]就是下面左边和下面右边的最小值加上ij结点本身的值
 *
 *  状态转移方程：
 *  f[i][j] = min{f[i+1][j], f[i+1][j+1]} + m[i][j]
 *
 *  初始值和边界：
 *  i是最下面的一行时，f[i][j]=m[i][j]
 *
 *  计算顺序从低往上f[i][j],f[i-1][j-1]...f[0][0]
 */
public class Triangle {

    /**
     * 递归
     * @param m
     * @return
     */
    public static int triangle1(int[][] m) {
        return triangle1(0, 0, m) + m[0][0];
    }

    /**
     * 备忘录递归
     * @param m
     * @return
     */
    public static int triangle2(int[][] m) {
        Map<String, Integer> map = new HashMap<>();
        return triangle2(0, 0, m, map) + m[0][0];
    }

    /**
     * 动态规划
     * @param m
     * @return
     */
    public static int triangle(int[][] m) {
        int row = m.length;

        // f中保存每一行各个结点到最后的最小的路径和，初始值是最后一行
        int[] f = new int[m[m.length - 1].length];
        for (int i = 0; i < m[m.length - 1].length; i++) {
            f[i] = m[m.length - 1][i];
        }

        // 从倒数第二行开始
        for (int i = row - 2; i >= 0; i--) {
            for (int j = 0; j < m[j].length - 1; j++) {
                f[j] = Math.min(f[j], f[j + 1]) + m[i][j];
            }
        }
        return f[0];
    }

    public static int triangle3(int[][] m) {
        int[][] f = new int[m.length][m[0].length];
        for (int i = 0; i < m[m.length - 1].length; i++) {
            f[m.length - 1][i] = m[m.length - 1][i];
        }

        for (int i = m.length - 2; i >= 0; i--) {
            for (int j = 0; j < m[j].length - 1; j++) {
                f[i][j] = Math.min(f[i + 1][j], f[i + 1][j + 1]) + m[i][j];
            }
        }
        return f[0][0];
    }

    private static int triangle2(int i, int j, int[][] m, Map<String, Integer> map) {
        String key = i + "-" + j;
        if (map.get(key) != null) {
            return map.get(key);
        }
        int left = triangle1(i + 1, j, m) + m[i + 1][j];
        int right = triangle1(i + 1, j + 1, m) + m[i + 1][j + 1];
        int result = Math.min(left, right);
        map.put(key, result);

        return result;
    }

    private static int triangle1(int i, int j, int[][] m) {
        if (i >= m.length - 1) {
            return 0;
        }
        int left = triangle1(i + 1, j, m) + m[i + 1][j];
        int right = triangle1(i + 1, j + 1, m) + m[i + 1][j + 1];
        return Math.min(left, right);
    }

    public static void main(String[] args) {
        int[][] triangle = {
            {2, 0, 0, 0},
            {3, 4, 0, 0},
            {6, 5, 7, 0},
            {4, 1, 8, 3}
        };

        System.out.println(triangle1(triangle));
        System.out.println(triangle2(triangle));
        System.out.println(triangle(triangle));
        System.out.println(triangle3(triangle));
    }
}
