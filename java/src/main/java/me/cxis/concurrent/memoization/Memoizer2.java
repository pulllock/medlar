package me.cxis.concurrent.memoization;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by cx on 7/14/16.
 * 输入类型为A,输出类型为V
 */
public class Memoizer2<A,V> implements Computable<A,V> {
    private final Map<A,V> cache = new ConcurrentHashMap<A,V>();
    private final Computable<A,V> c;

    public Memoizer2(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if(result == null){
            result = c.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }
}
