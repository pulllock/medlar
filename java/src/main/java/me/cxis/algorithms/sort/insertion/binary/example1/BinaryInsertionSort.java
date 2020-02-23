package me.cxis.algorithms.sort.insertion.binary.example1;

import java.util.Arrays;

/**
 * 折半插入排序
 */
public class BinaryInsertionSort {

    public void binaryInsertionSort(Integer[] array) {
        // 已排序元素下标
        int i;

        // j待排序元素下标
        for (int j = 1; j < array.length; j++) {
            // 待排序的元素
            Integer temp = array[j];
            int low = 0;
            int high = j - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (temp > array[mid]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            // low后面的数组整体往后移
            for (i = j; i > low; i--) {
                array[i] = array[i - 1];
            }
            array[low] = temp;
        }
    }

    public static void main(String[] args) {
        Integer[] array = {8, 3, 6, 2, 4, 5, 7, 1, 9, 0};
        System.out.println("before: " + Arrays.asList(array));
        BinaryInsertionSort sort = new BinaryInsertionSort();
        sort.binaryInsertionSort(array);
        System.out.println("after: " + Arrays.asList(array));
    }
}
