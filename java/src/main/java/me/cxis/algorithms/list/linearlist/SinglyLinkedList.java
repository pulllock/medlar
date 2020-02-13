package me.cxis.algorithms.list.linearlist;

/**
 * Created by cheng.xi on 2017-05-30 15:11.
 * 单链表
 */
public class SinglyLinkedList<E> implements LList<E> {

    private Node<E> head;



    public SinglyLinkedList(Node<E> head){
        this.head = head;
    }

    public SinglyLinkedList(){
        this.head = null;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public int length() {
        int i = 0;
        Node<E> p = head;
        while(p != null){
            i++;
            p = p.next;
        }
        return i;
    }

    @Override
    public E get(int index) {
        if(head != null && index >= 0){
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
        if(head != null && index >= 0 && e != null){
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
        return add(Integer.MAX_VALUE,e);
    }

    @Override
    public boolean add(int index, E e) {
        if( e ==null){
            return false;
        }
        if(head == null || index <= 0){
            Node<E> q = new Node<>(e);
            q.next = head;
            head = q;
        }else {
            int j = 0;
            Node<E> p = head;
            while(p != null && j < index - 1){
                j++;
                p = p.next;
            }
            Node<E> q = new Node<>(e);
            q.next = p.next;
            p.next = q;
        }
        return true;
    }

    @Override
    public E remove(int index) {
        E old = null;
        if(head != null && index >= 0){
            if(index == 0){
                old = head.data;
                head = head.next;
            }else {
                int j = 0;
                Node<E> p = head;
                while(p != null && j < index - 1){
                    j++;
                    p = p.next;
                }
                if(p.next != null){
                    old = p.next.data;
                    p.next = p.next.next;
                }
            }
        }
        return old;
    }

    @Override
    public void clear() {
        this.head = null;
    }
}
