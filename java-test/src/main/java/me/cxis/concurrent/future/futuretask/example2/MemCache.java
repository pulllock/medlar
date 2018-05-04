package me.cxis.concurrent.future.futuretask.example2;

import java.util.concurrent.*;

public class MemCache<K, V> {

    private final ConcurrentHashMap<K, Future<V>> cache = null;

    public V get(K k) {
        while (true) {
            Future<V> f = cache.get(k);
            if (f == null) {
                Callable<V> callable = () -> getResultFromDB(k);
                FutureTask<V> futureTask = new FutureTask<>(callable);

                f = cache.putIfAbsent(k, futureTask);

                if (f == null) {
                    f = futureTask;
                    futureTask.run();
                }
            }

            try {
                return f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
                cache.remove(k, f);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private V getResultFromDB(K k) {
        return null;
    }

}
