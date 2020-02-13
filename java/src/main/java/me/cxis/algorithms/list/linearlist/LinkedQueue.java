package me.cxis.algorithms.list.linearlist;

/**
 * Created by cheng.xi on 2017-05-31 22:08.
 */
public class LinkedQueue<E> implements QQueue<E> {
    private Node<E> front;
    private Node<E> tail;

    public LinkedQueue(){
        front = tail = null;
    }

    @Override
    public boolean isEmpty() {
        return front == null && tail == null;
    }

    @Override
    public boolean enqueue(E e) {
        if(e == null){
            return false;
        }
        Node<E> q = new Node<>(e);
        if(!isEmpty()){
            tail.next = q;
        }else {
            front = q;
        }
        tail = q;
        return true;
    }

    @Override
    public E deququ() {
        if(!isEmpty()){
            E e = front.data;
            front = front.next;
            if(front == null){
                tail = null;
            }
            return e;
        }
        return null;
    }
}
