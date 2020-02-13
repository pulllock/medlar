package me.cxis.algorithms.list.linearlist;

/**
 * Created by cheng.xi on 2017-05-31 17:22.
 * 双向链表结点
 */
public class DLinkNode<E> {
    public E data;
    public DLinkNode<E> prev;
    public DLinkNode<E> next;

    public DLinkNode(E data, DLinkNode<E> prev, DLinkNode<E> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    public DLinkNode(E data) {
        this(data,null,null);
    }

    public DLinkNode() {
        this(null,null,null);
    }
}
