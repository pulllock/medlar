package me.cxis.concurrent.executor.example1;

public class UseThreadPool {

    private ThreadPool pool;

    private static UseThreadPool useThreadPool = new UseThreadPool();

    private UseThreadPool() {
        int workQueueSize = 80;
        int coreSize = 4;
        int maxSize = 10;

        pool = new ThreadPool(workQueueSize, coreSize, maxSize, ThreadPool.ARRAY_QUEUE);
    }

    public static UseThreadPool getPool() {
        return useThreadPool;
    }

    public void execute(Runnable runnable) {
        pool.execute(runnable);
    }

    public void execute(String name) {
        execute(new ThreadRunnable(name));
    }
}
