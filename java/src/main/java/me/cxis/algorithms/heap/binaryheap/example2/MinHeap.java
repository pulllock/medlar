package me.cxis.algorithms.heap.binaryheap.example2;

import java.util.Arrays;

/**
 * 索引从1开始，对于任意一个位置n，其左儿子的位置是2n，右儿子的位置是2n+1，父亲是n/2
 * 索引从0开始，对于任意一个位置n，其左儿子的位置是2n+1，右儿子的位置是2n+2，父亲是(n-1)/2
 */
public class MinHeap {

    private int[] heap;

    private int currentIndex;

    public MinHeap(int size) {
        heap = new int[size];
    }

    public void insert(int data) {
        int current = ++currentIndex;
        for (heap[0] = data; data < heap[current / 2]; current /= 2) {
            heap[current] = heap[current / 2];
        }
        heap[current] = data;
    }

    public static void main(String[] args) {
        // [26, 31, 58, 41, 53, 59, 97]
        MinHeap minHeap = new MinHeap(10);
        minHeap.insert(31);
        minHeap.insert(41);
        minHeap.insert(59);
        minHeap.insert(26);
        minHeap.insert(53);
        minHeap.insert(58);
        minHeap.insert(97);

        Arrays.stream(minHeap.heap).forEach(System.out::println);
    }
}
