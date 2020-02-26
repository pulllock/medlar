package me.cxis.algorithms.dynamic_programming.fibonacci;

public class Fibonacci {

    public int fib(int n) {
        if (n <= 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 自定向下备忘录法
     * @param n
     * @return
     */
    public int fib1(int n) {
        if (n <= 0) {
            return 0;
        }

        int[] memo = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }

        return fib1(n, memo);
    }

    private int fib1(int n, int[] memo) {
        if (memo[n] != -1) {
            return memo[n];
        }
        if (n <= 2) {
            memo[n] = 1;
        } else {
            memo[n] = fib1(n - 1, memo) + fib1(n - 2, memo);
        }
        return memo[n];
    }

    /**
     * 自底向上方法
     * @param n
     * @return
     */
    public int fib2(int n) {
        if (n <= 0) {
            return n;
        }

        int[] memo = new int[n + 1];
        memo[0] = 0;
        memo[1] = 1;

        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }

    public static void main(String[] args) {
        int n = 6;
        Fibonacci fibonacci = new Fibonacci();
        long fibStart = System.nanoTime();
        System.out.println(fibonacci.fib(n));
        System.out.println("fib: " + (System.nanoTime() - fibStart));

        long fib1Start = System.nanoTime();
        System.out.println(fibonacci.fib1(n));
        System.out.println("fib1: " + (System.nanoTime() - fib1Start));

        long fib2Start = System.nanoTime();
        System.out.println(fibonacci.fib2(n));
        System.out.println("fib2: " + (System.nanoTime() - fib2Start));
    }
}
