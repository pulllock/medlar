package me.cxis.algorithms.list.linearlist;

/**
 * Created by cheng.xi on 2017-05-31 17:34.
 * 顺序栈
 */
public class SeqStack<E> implements SStack<E> {

    private Object[] value;//使用数组存储元素
    private int top;//栈顶元素的下标

    public SeqStack(int capacity) {
        value = new Object[capacity];
        top = -1;
    }

    public SeqStack(){
        this(10);
    }

    @Override
    public boolean isEmpty() {
        return this.top == -1;
    }

    @Override
    public boolean push(E e) {
        if(e == null){
            return false;
        }
        if(top == value.length - 1){
            //扩容
            Object[] temp = value;
            value = new Object[temp.length * 2];
            for(int i = 0; i < value.length; i++){
                value[i] = temp[i];
            }
        }
        top++;
        value[top] = e;
        return true;
    }

    @Override
    public E pop() {
        if(!isEmpty()){
            return (E)value[top--];
        }
        return null;
    }

    @Override
    public E get() {
        if(!isEmpty()){
            return (E)value[top];
        }
        return null;
    }
}
