package me.cxis.concurrent.memoization;

import java.util.concurrent.*;

/**
 * Created by cx on 7/14/16.
 */
public class Memoizer<A,V> implements Computable<A,V> {
    private final ConcurrentMap<A,Future<V>> cache = new ConcurrentHashMap<A,Future<V>>();
    private final Computable<A,V> c;

    public Memoizer(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if(f == null){
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };

            FutureTask<V> ft = new FutureTask<V>(eval);
            f = cache.putIfAbsent(arg,ft);
            if(f == null){
                f = ft;
                ft.run();
            }
        }
        try {
            return f.get();
        }catch (CancellationException e){
            cache.remove(arg,f);
            return null;
        }catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
