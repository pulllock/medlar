package me.cxis.algorithms.linearlist;

/**
 * Created by cheng.xi on 2017-05-31 17:32.
 * 栈
 */
public interface SStack<E> {

    boolean isEmpty();

    boolean push(E e);

    E pop();

    E get();
}
