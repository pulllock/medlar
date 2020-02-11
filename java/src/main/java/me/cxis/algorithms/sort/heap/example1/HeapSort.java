package me.cxis.algorithms.sort.heap.example1;

import java.util.Arrays;

public class HeapSort<T extends Comparable<? super T>> {

    /**
     * 大堆
     * @param array
     */
    public void heapSort(T[] array) {
        /**
         * 将array当做一个无序的堆，进行调整
         * 从最后一个非叶子节点开始调整，一直向前挨个找非叶子节点调整，最后一个非叶子节点：array.length / 2 -1
         */
        for (int start = array.length / 2 - 1; start >= 0; start--) {
            percolateDown(array, start, array.length);
        }

        // 上面已经将无序堆调整成一个大顶堆了，需要将最大的根节点与最后的元素交换，然后再次调整堆
        for (int i = array.length - 1; i > 0; i--) {
            T temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            percolateDown(array, 0, i);
        }

    }

    private void percolateDown(T[] array, int start, int length) {
        int child;
        T father;
        for (father = array[start]; getLeftChild(start) < length; start = child) {
            child = getLeftChild(start);
            // leftChild = length - 1 说明只有左孩子
            if (child != length -1 && array[child].compareTo(array[child + 1]) < 0) {
                child++;
            }

            if (father.compareTo(array[child]) < 0) {
                array[start] = array[child];
            } else {
                break;
            }
        }
        array[start] = father;
    }

    private int getLeftChild(int n) {
        return 2 * n + 1;
    }

    public static void main(String[] args) {
        HeapSort<Integer> heapSort = new HeapSort<>();
        Integer[] array = new Integer[]{31, 41, 59, 26, 53, 58, 97};
        System.out.println("before: " + Arrays.asList(array));
        heapSort.heapSort(array);
        System.out.println("after: " + Arrays.asList(array));
    }
}
