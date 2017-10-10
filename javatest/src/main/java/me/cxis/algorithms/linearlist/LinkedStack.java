package me.cxis.algorithms.linearlist;

/**
 * Created by cheng.xi on 2017-05-31 19:40.
 */
public class LinkedStack<E> implements SStack<E> {

    private Node<E> top;

    public LinkedStack() {
        top = null;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public boolean push(E e) {
        if(e == null){
            return false;
        }
        top = new Node<>(e,top);
        return true;
    }

    @Override
    public E pop() {
        if(!isEmpty()){
            E e = top.data;
            top = top.next;
            return e;
        }
        return null;
    }

    @Override
    public E get() {
        if(!isEmpty()){
            return top.data;
        }
        return null;
    }
}
