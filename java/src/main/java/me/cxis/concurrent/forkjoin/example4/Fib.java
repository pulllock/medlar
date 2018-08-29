package me.cxis.concurrent.forkjoin.example4;

public class Fib {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(fib(40));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static long fib(long n) {
        if (n <= 2) {
            return n;
        } else {
            return fib(n -1) + fib(n -2);
        }
    }
}
