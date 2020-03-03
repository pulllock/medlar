package me.cxis.algorithms.dynamic_programming.fibonacci;

import java.util.HashMap;
import java.util.Map;

/**
 * n = 0 f(n) = 0
 * n = 1 f(n) = 1
 * f(n) = f(n - 1) + f(n - 2)
 */
public class Fibonacci1 {

    public static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * 自顶向下备忘录模式
     * @param n
     * @param map
     * @return
     */
    public static int fibonacci1(int n, Map<Integer, Integer> map) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        if (map.get(n) != null) {
            return map.get(n);
        }
        int result = fibonacci1(n - 1, map) + fibonacci1(n - 2, map);
        map.put(n, result);
        return result;
    }

    /**
     * 动态规划
     * @param n
     * @return
     */
    public static int fibonacci2(int n) {
        int[] a = new int[n + 1];
        a[0] = 0;
        a[1] = 1;

        for (int i = 2; i <= n; i++) {
            a[i] = a[i - 1] + a[i - 2];
        }
        return a[n];
    }

    public static int fibonacci3(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        int prePre = 0;
        int pre = 1;
        int result = 0;
        for (int i = 2; i <= n; i++) {
            result = pre + prePre;
            prePre = pre;
            pre = result;
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 6;
        System.out.println(fibonacci(n));
        System.out.println(fibonacci1(n, new HashMap<>()));
        System.out.println(fibonacci2(n));
        System.out.println(fibonacci3(n));
    }
}
