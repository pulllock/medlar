package me.cxis.algorithms.sort.insertion.binary.example2;

import java.util.Arrays;

/**
 * 二分插入排序
 */
public class InsertionSort {

    public void insertionSort(Integer[] source) {

        for (int i = 1; i < source.length; i++) {
            Integer temp = source[i];

            int left = 0;
            int right = i - 1;

            while (left <= right) {
                int mid = (left + right) >>> 1;
                if (temp > source[mid]) {
                     left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            // left到i - 1之间的元素要往右边移动
            for (int j = i - 1; j >= left; j--) {
                source[j + 1] = source[j];
            }

            source[left] = temp;
        }
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[] {3, 4, 6, 2, 3, 1, 4, 10, 48, 2, 3, 12, 14};
        new InsertionSort().insertionSort(array);
        System.out.println(Arrays.toString(array));
    }
}
