package cheng.xi.mybatis.test;

/**
 * Created by justdoit on 15-5-22.
 */
public class SequenceList<T> {
    private int DEFAULT_SIZE = 16;
    private int capacity;
    private Object[] elementData;
    private int size = 0;

    public SequenceList(){
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }

    public SequenceList(T element){
        this();
        elementData[0] = element;
        size ++;
    }

    public SequenceList(T element,int initSize){
        capacity = 1;
        while(capacity < initSize){
            capacity <<= 1;
        }
        elementData = new Object[capacity];
        elementData[0] = element;
        size ++;
    }

    public int length(){
        return size;
    }

    public T get(int i){
        if(i < 0 || i> size -1){
            throw new IndexOutOfBoundsException("越界");
        }
        return (T) elementData[i];
    }

    public int locate(T element){
        for(int i = 0; i < size; i++){
            if(elementData[i].equals(element)){
                return i;
            }
        }
        return -1;
    }

    public void insert(T element,int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("越界");
        }
        ensureCapacity(size + 1);
        System.arraycopy(elementData,index,elementData,index + 1,size - index);
        elementData[index] = element;
        size++;
    }

    public void add(T element){
        insert(element,size);
    }

    private void ensureCapacity(int minCapacity){
        if(size < minCapacity){

        }
    }
}
