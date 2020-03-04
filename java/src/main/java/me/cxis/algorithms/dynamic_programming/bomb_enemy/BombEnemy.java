package me.cxis.algorithms.dynamic_programming.bomb_enemy;

/**
 * 有一个M*N网格，每个格子可能是空的，可能有一个敌人，可能有一堵墙，只能在某个空格子里放一个炸弹，炸弹会
 * 炸死所有同行同列的敌人，但是不能穿透墙，问最多能炸死几个敌人。
 *
 * 0 E 0 0
 * E 0 W E
 * 0 E 0 0
 *
 * 输出：3
 *
 * 每个炸弹可以往四个方向传播，可以先分析向上能炸死多少敌人
 *
 * 确定状态：
 * 假设在有敌人或有墙的格子也能放炸弹：
 * - 有敌人的格子：格子里的敌人被炸死，并继续向上爆炸
 * - 有墙的格子：炸弹不能炸死任何敌人
 *
 * 在(i,j)格放一个炸弹，它向上能炸死的敌人数：
 * - (i,j)为空地：(i-1,j)格向上能炸死的敌人数
 * - (i,j)为敌人：(i-1,j)格向上能炸死的敌人数+1
 * - (i,j)为墙：0
 *
 * 需要知道(i-1,j)格向上能炸死的敌人数
 * 原问题：(i,j)格向上能炸死的敌人数
 *
 * 子问题：(i-1,j)格向上能炸死的敌人数
 *
 * 状态：
 * up[i][j]表示(i,j)格放一个炸弹向上能炸死的敌人数
 *
 * 状态转移方程：
 *          |-- up[i-1][j] 如果(i,j)是空地
 *          |
 * up[i][j] |-- up[i-1][j]+1 如果(i,j)是敌人
 *          |
 *          |-- 0 如果(i,j)是墙
 *
 * 初始条件和边界：
 * up[0][j]=0 如果(0,j)不是敌人
 * up[0][j]=1 如果(0,j)是敌人
 *
 * 时间和空间复杂度：O(MN)
 *
 * 因为有4个方向，需要计算down[i][j] left[i][j] right[i][j]
 *
 * (i,j)如果是空地，放一个炸弹最多能炸死的敌人是：
 * up[i][j] + down[i][j] + left[i][j] + right[i][j]
 *
 * 最后取最大值
 * 时间和空间复杂度：O(MN)
 */
public class BombEnemy {

    public static int bombEnemy(char[][] a) {
        int m = a.length;
        int n = a[0].length;

        int[][] up = new int[m][n];
        int[][] down = new int[m][n];
        int[][] left = new int[m][n];
        int[][] right = new int[m][n];

        int result;
        // 算向上的
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果本格是墙
                if (a[i][j] == 'W') {
                    up[i][j] = 0;
                    continue;
                }
                // 如果本格是敌人
                if (a[i][j] == 'E') {
                    up[i][j] = 1;
                } else {
                    up[i][j] = 0;
                }

                if (i > 0) {
                    up[i][j] += up[i - 1][j];
                }
            }
        }

        // 算向下的
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                // 如果本格是墙
                if (a[i][j] == 'W') {
                    down[i][j] = 0;
                    continue;
                }
                // 如果本格是敌人
                if (a[i][j] == 'E') {
                    down[i][j] = 1;
                } else {
                    down[i][j] = 0;
                }

                if (i < m - 1) {
                    down[i][j] += down[i + 1][j];
                }
            }
        }

        // 算向左的
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果本格是墙
                if (a[i][j] == 'W') {
                    left[i][j] = 0;
                    continue;
                }
                // 如果本格是敌人
                if (a[i][j] == 'E') {
                    left[i][j] = 1;
                } else {
                    left[i][j] = 0;
                }

                if (j > 0) {
                    left[i][j] += left[i][j - 1];
                }
            }
        }

        // 算向右的
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                // 如果本格是墙
                if (a[i][j] == 'W') {
                    right[i][j] = 0;
                    continue;
                }
                // 如果本格是敌人
                if (a[i][j] == 'E') {
                    right[i][j] = 1;
                } else {
                    right[i][j] = 0;
                }

                if (j < n - 1) {
                    right[i][j] += right[i][j + 1];
                }
            }
        }

        result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 找空地
                if (a[i][j] == '0') {
                    result = Math.max(result, up[i][j] + down[i][j] + left[i][j] + right[i][j]);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        char[][] a = {
                {'0', 'E', '0', '0'},
                {'E', '0', 'W', 'E'},
                {'0', 'E', '0', '0'}
        };

        System.out.println(bombEnemy(a));
    }
}
