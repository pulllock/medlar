package me.cxis.concurrent.executor.example1;

import java.util.concurrent.*;

public class ThreadPool {

    public static final int ARRAY_QUEUE = 0;

    public static final int LINKED_QUEUE = 1;

    private ThreadPoolExecutor executor;

    private BlockingQueue<Runnable> workQueue;

    public ThreadPool(final int workQueueSize, final int coreSize,
                      final int maxSize, int queueType) {
        this(workQueueSize, coreSize, maxSize, queueType, null);
    }

    public ThreadPool(int workQueueSize, int coreSize,
                      int maxSize, int queueType,
                      RejectedExecutionHandler policy) {
        workQueue = createQueue(queueType, workQueueSize);
        executor = new ThreadPoolExecutor(
            coreSize,
            maxSize,
            60,
            TimeUnit.SECONDS,
            workQueue,
            policy != null ? policy : new ThreadPoolExecutor.AbortPolicy()
        );
    }

    public void execute(Runnable runnable) {
        if (workQueue.size() > 4) {
            System.out.println("等待的线程大小：" + runnable.getClass().getSimpleName() + ":" + workQueue.size());
        }

        executor.execute(runnable);
    }

    public BlockingQueue<Runnable> getQueue() {
        return executor.getQueue();
    }

    private BlockingQueue<Runnable> createQueue(int queueType, int workQueueSize) {
        return queueType == LINKED_QUEUE ?
            new LinkedBlockingQueue<>(workQueueSize) : new ArrayBlockingQueue<>(workQueueSize);
    }
}
