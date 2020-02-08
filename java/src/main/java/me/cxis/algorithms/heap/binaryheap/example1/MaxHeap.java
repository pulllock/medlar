package me.cxis.algorithms.heap.binaryheap.example1;

import java.util.ArrayList;
import java.util.List;

/**
 * 索引从1开始，对于任意一个位置n，其左儿子的位置是2n，右儿子的位置是2n+1，父亲是n/2
 * 索引从0开始，对于任意一个位置n，其左儿子的位置是2n+1，右儿子的位置是2n+2，父亲是(n-1)/2
 * @param <T>
 */
public class MaxHeap<T extends Comparable> {

    private List<T> heap;

    public MaxHeap() {
        heap = new ArrayList<>();
    }

    /**
     * 插入数据
     * @param data
     */
    public void insert(T data) {
        int size = heap.size();
        heap.add(data);
        percolateUp(size);
    }


    /**
     * 上滤
     * @param start
     */
    public void percolateUp(int start) {
        int current = start;
        int parent = (current - 1) / 2;

        T temp = heap.get(current);

        while (current > 0) {
            int compare = heap.get(parent).compareTo(temp);
            if (compare >= 0) {
                break;
            } else {
                heap.set(current, heap.get(parent));
                current = parent;
                parent = (parent - 1) / 2;
            }
        }

        heap.set(current, temp);
    }
}
