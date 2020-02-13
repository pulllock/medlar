package me.cxis.algorithms.list.linearlist;

/**
 * Created by cheng.xi on 2017-05-30 15:10.
 * 顺序表
 */
public class SeqList<E> implements LList<E> {

    private Object[] table;
    private int size;

    public SeqList() {
        this(16);
    }

    public SeqList(int capacity) {
        this.table = new Object[capacity];
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int length() {
        return this.size;
    }

    @Override
    public E get(int index) {
        if(index >= 0 && index < this.size){
            return (E) table[index];
        }
        return null;
    }

    @Override
    public E set(int index, E e) {
        if(index >= 0 && index< this.size && null != e){
            E old = (E)table[index];
            table[index] = e;
            return old;
        }
        return null;
    }

    @Override
    public boolean add(E e) {
        return add(size,e);
    }

    @Override
    public boolean add(int index, E e) {
        if(null == e){
            return false;
        }
        //数组已经满了，需要扩容，原来的两倍
        if(this.size == table.length){
            Object[] oldTable = table;
            table = new Object[oldTable.length * 2];
            for(int i = 0; i < oldTable.length; i++){
                table[i] = oldTable[i];
            }
        }

        if(index < 0){
            return false;
        }

        if(index > size){
            index = size;
        }

        for(int j = size -1; j >= index; j--){
            table[j + 1] = table[j];
        }

        table[index] = e;
        size ++;
        return true;
    }

    @Override
    public E remove(int index) {
        if(size !=0 && index >= 0 && index < size){
            E old = (E)table[index];
            for(int j = index; j < size; j++){
                table[j] = table[j + 1];
            }
            table[size - 1] = null;
            size--;
            return old;
        }
        return null;
    }

    @Override
    public void clear() {
        if(size != 0){
            for(int i = 0; i < size; i++){
                table[i] = null;
            }
            this.size = 0;
        }
    }
}
