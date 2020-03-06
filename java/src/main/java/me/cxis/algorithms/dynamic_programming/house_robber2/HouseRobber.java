package me.cxis.algorithms.dynamic_programming.house_robber2;

/**
 * 有一圈N栋房子，房子i-1里有a[i]个金币，一个窃贼想偷取一些房子里的金币，不能偷相邻的房子，
 * 问最多能偷取多少金币？
 *
 * 输入：{3,8,4}
 * 输出：8
 *
 * 房子0和房子n-1相邻，只能偷其中的一个，可以分析没偷房子0和没偷房子n-1两种情况。
 *
 * 如果没偷房子0，则房子n-1能偷，则最优策略就是1到n-1栋房子的最优策略
 *
 * 如果没偷房子n-1，则房子0能偷，最优策略就是0到n-2栋房子的最优策略
 *
 * 最后还是转化成了HouseRobber
 */
public class HouseRobber {

}
