package me.cxis.algorithms.linearlist;

/**
 * Created by cheng.xi on 2017-05-30 21:57.
 * 带头结点的单链表
 */
public class HSLinkedList<E>  implements LList<E>{
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public HSLinkedList() {
        head = new Node<>(null);
        tail = head;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return head.next == null;
    }

    @Override
    public int length() {
        return size;
    }

    @Override
    public E get(int index) {
        if(head.next != null && index >= 0){
            Node<E> p = head;
            int j = 0;
            while(p != null && j < index){
                j++;
                p = p.next;
            }
            if(p != null){
                return (E)p.data;
            }
        }
        return null;
    }

    @Override
    public E set(int index, E e) {
        if(head.next != null && index >= 0 && e != null){
            int j = 0;
            Node<E> p = head;
            while(p != null && j < index){
                j++;
                p = p.next;
            }
            if(p != null){
                E old = p.data;
                p.data = e;
                return old;
            }
        }
        return null;
    }

    @Override
    public boolean add(E e) {
        if( e == null){
            return false;
        }

        this.tail.next = new Node<>(e);
        tail = tail.next;
        size++;
        return true;
    }

    @Override
    public boolean add(int index, E e) {
        if(e == null){
            return false;
        }
        if(index >= size){
            return add(e);
        }else {
            int j = 0;
            Node<E> p = head;
            while(p.next != null && j < index){
                j++;
                p = p.next;
            }
            Node<E> q = new Node<>(e,p.next);
            p.next = p;
            size++;
            return  true;
        }
    }

    @Override
    public E remove(int index) {
        E old = null;
        if(index >= 0){
            int j = 0;
            Node<E> p = head;
            while(p.next != null && j < index){
                j++;
                p = p.next;
            }
            if(p.next != null){
                old = p.next.data;
                if(p.next == tail){
                    p = tail;
                }
                p.next = p.next.next;
                size--;
            }
        }
        return old;
    }

    @Override
    public void clear() {
        head.next = null;
        tail = head;
        size = 0;
    }
}
