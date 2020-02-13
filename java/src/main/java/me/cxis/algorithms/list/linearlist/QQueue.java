package me.cxis.algorithms.list.linearlist;

/**
 * Created by cheng.xi on 2017-05-31 21:25.
 * 队列
 */
public interface QQueue<E> {
    boolean isEmpty();

    boolean enqueue(E e);

    E deququ();
}
