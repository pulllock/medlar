package me.cxis.algorithms.list.linearlist;

/**
 * Created by cheng.xi on 2017-05-31 21:26.
 */
public class SeqQueue<E> implements QQueue<E> {

    private Object[] value;
    private int front;
    private int tail;

    public SeqQueue(int capacity){
        value = new Object[capacity];
        front = tail = 0;
    }

    public SeqQueue(){
        this(10);
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public boolean enqueue(E e) {
        if(e == null){
            return false;
        }
        if(front == (tail + 1) % value.length){
            //队列满，扩容
            Object[] temp = value;
            value = new Object[temp.length * 2];
            int i = front;
            int j = 0;
            while(i != tail){
                value[j] = temp[j];
                i = (i + 1) % temp.length;
                j++;
            }
            front = 0;
            tail = j;
        }
        value[tail] = e;
        tail = (tail + 1) % value.length;
        return true;
    }

    @Override
    public E deququ() {
        if(!isEmpty()){
            E e = (E)value[front];
            front = (front + 1) % value.length;
            return e;
        }
        return null;
    }
}
