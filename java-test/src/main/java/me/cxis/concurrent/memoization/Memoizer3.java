package me.cxis.concurrent.memoization;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by cx on 7/14/16.
 */
public class Memoizer3<A,V> implements Computable<A,V> {
    private final Map<A,Future<V>> cache = new ConcurrentHashMap<A,Future<V>>();
    private final Computable<A,V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if(f == null){
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws InterruptedException {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<V>(eval);
            f = ft;
            cache.put(arg,ft);
            ft.run();//在这里调用c.compute
        }
        try {
            return f.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
