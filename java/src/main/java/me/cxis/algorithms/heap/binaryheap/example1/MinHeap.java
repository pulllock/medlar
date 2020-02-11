package me.cxis.algorithms.heap.binaryheap.example1;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<T extends Comparable> {

    private List<T> heap;

    public MinHeap() {
        heap = new ArrayList<>();
    }

    public void insert(T data) {
        int size = heap.size();
        heap.add(data);
        percolateUp(size);
    }

    public void percolateUp(int start) {
        int current = start;
        int parent = (current - 1) / 2;

        T temp = heap.get(current);

        while (current > 0) {
            int compare = heap.get(parent).compareTo(temp);
            if (compare <= 0) {
                break;
            } else {
                heap.set(current, heap.get(parent));
                current = parent;
                parent = (parent - 1) / 2;
            }
        }

        heap.set(current, temp);
    }

    public void deleteMin() {
        T min = heap.set(0, heap.remove(heap.size() - 1));
        percolateDown(0);
    }

    public void percolateDown(int start) {
        int child;
        T temp = heap.get(start);
        int currentSize = heap.size() - 1;
        for (; (start * 2 + 1) <= currentSize; start = child) {
            child = (start * 2 + 1);
            if (child != currentSize && heap.get(child).compareTo(heap.get(child + 1)) > 0) {
                child++;
            }

            if (heap.get(child).compareTo(temp) < 0) {
                heap.set(start, heap.get(child));
            }
        }

        heap.set(start, temp);
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        minHeap.insert(31);
        minHeap.insert(41);
        minHeap.insert(59);
        minHeap.insert(26);
        minHeap.insert(53);
        minHeap.insert(58);
        minHeap.insert(97);

        // [26, 31, 58, 41, 53, 59, 97]
        System.out.println(minHeap.heap);
        minHeap.deleteMin();
        // [31, 41, 58, 97, 53, 59]
        System.out.println(minHeap.heap);
    }
}
