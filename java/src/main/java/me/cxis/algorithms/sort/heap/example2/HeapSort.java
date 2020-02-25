package me.cxis.algorithms.sort.heap.example2;

import java.util.Arrays;

public class HeapSort {

    public void heapSort(Integer[] array) {
        /**
         * 将原数组构建成一个大顶堆
         * 把原数组当做一个无序堆，从最后一个节点 array.length / 2 -1开始调整
         */
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjust(array, i, array.length);
        }

        // 构建完大顶堆后，将堆顶记录和最后一个记录交换，然后继续调整剩下的堆
        for (int i = array.length - 1; i > 0; i--) {
            Integer temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            adjust(array, 0, i);
        }
    }

    private void adjust(Integer[] array, int start, int length) {
        Integer fatherData;
        int childIndex;

        for (fatherData = array[start]; getLeftChild(start) < length; start = childIndex) {
            childIndex = getLeftChild(start);

            /**
             * 如果只有左孩子（childIndex = length - 1）,则可以直接比较左孩子和父亲的大小
             * 如果有右孩子，需要比较一下左右孩子的大小，如果右孩子大，则使用右孩子和父亲比大小，否则用左孩子和父亲比大小
             */
            if (childIndex != length - 1 && array[childIndex] < array[childIndex + 1]) {
                childIndex++;
            }

            // 确定了较大的孩子位置，就可以和父节点进行比较
            if (fatherData < array[childIndex]) {
                array[start] = array[childIndex];
            } else {
                break;
            }
        }
        array[start] = fatherData;
    }

    private int getLeftChild(int start) {
        return 2 * start + 1;
    }

    public static void main(String[] args) {
        HeapSort sort = new HeapSort();
        Integer[] array = new Integer[]{31, 41, 59, 26, 53, 58, 97};
        System.out.println("before: " + Arrays.asList(array));
        sort.heapSort(array);
        System.out.println("after: " + Arrays.asList(array));
    }
}
