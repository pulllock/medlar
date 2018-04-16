package me.cxis.concurrent.executor.example1;

public class TestThread {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            UseThreadPool.getPool().execute("num:" + i);
        }
    }
}
