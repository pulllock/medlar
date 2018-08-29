package me.cxis.algorithms.linearlist;

/**
 * Created by cheng.xi on 2017-05-30 15:07.
 * 线性表接口
 */
public interface LList<E> {

    boolean isEmpty();

    int length();

    E get(int index);

    E set(int index, E e);

    boolean add(E e);

    boolean add(int index, E e);

    E remove(int index);

    void clear();

}
