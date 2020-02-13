package me.cxis.algorithms.list.linearlist;

/**
 * Created by cheng.xi on 2017-05-30 21:58.
 */
public class Node<E>{
    public E data;
    public Node<E> next;

    public Node(E data,Node<E> next){
        this.data = data;
        this.next = next;
    }

    public Node(E data){
        this(data,null);
    }

    public Node(){
        this(null,null);
    }
}
