package me.cxis.concurrent.memoization;

/**
 * Created by cx on 7/14/16.
 */
public interface Computable<A,V> {
    V compute(A arg) throws InterruptedException;
}
