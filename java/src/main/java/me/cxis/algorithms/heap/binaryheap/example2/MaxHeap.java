package me.cxis.algorithms.heap.binaryheap.example2;

import java.util.Arrays;

public class MaxHeap {

    private int[] heap;

    private int currentIndex;

    public MaxHeap(int size) {
        heap = new int[size];
    }

    public void insert(int data) {
        int current = ++currentIndex;
        for (heap[0] = data; data > heap[current / 2]; current /= 2) {
            heap[current] = heap[current / 2];
        }

        heap[current] = data;
    }

    public int deleteMax() {
        int max = heap[1];
        heap[1] = heap[currentIndex--];
        percolateDown(1);
        return max;
    }

    private void percolateDown(int start) {
        int child;
        int temp = heap[start];
        for (; start * 2 <= currentIndex; start = child) {
            child = start * 2;

            if (child != currentIndex && heap[child] < heap[child + 1]) {
                child++;
            }

            if (temp < heap[child]) {
                heap[start] = heap[child];
            } else {
                break;
            }
        }
        heap[start] = temp;
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(10);
        maxHeap.insert(31);
        maxHeap.insert(41);
        maxHeap.insert(59);
        maxHeap.insert(26);
        maxHeap.insert(53);
        maxHeap.insert(58);
        maxHeap.insert(97);

        // [97, 53, 59, 26, 31, 41, 58]
        Arrays.stream(maxHeap.heap).forEach(System.out::println);
        maxHeap.deleteMax();
        System.out.println("------");
        // [59, 53, 58, 26, 31, 41]
        Arrays.stream(maxHeap.heap).forEach(System.out::println);
    }
}
