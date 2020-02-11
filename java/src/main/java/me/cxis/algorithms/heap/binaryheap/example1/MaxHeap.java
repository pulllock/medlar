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

    public T deleteMax() {
        T max = heap.set(0, heap.remove(heap.size() - 1));
        percolateDown(0);
        return max;
    }

    private void percolateDown(int start) {
        int child;
        T temp = heap.get(start);
        int currentSize = heap.size() - 1;
        for (; (start * 2 + 1) < currentSize; start = child) {
            child = (start * 2 + 1);

            if (child != currentSize && heap.get(child).compareTo(heap.get(child + 1)) < 0) {
                child++;

                if (temp.compareTo(heap.get(child)) < 0) {
                    heap.set(start, heap.get(child));
                } else {
                    break;
                }
            }
        }
        heap.set(start, temp);
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap();
        maxHeap.insert(31);
        maxHeap.insert(41);
        maxHeap.insert(59);
        maxHeap.insert(26);
        maxHeap.insert(53);
        maxHeap.insert(58);
        maxHeap.insert(97);

        // [97, 53, 59, 26, 31, 41, 58]
        System.out.println(maxHeap.heap);
        maxHeap.deleteMax();
        // [59, 53, 58, 26, 31, 41]
        System.out.println(maxHeap.heap);
    }
}
