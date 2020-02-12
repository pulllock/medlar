package me.cxis.algorithms.sort.quick.example1;

import java.util.Arrays;

public class QuickSort {

    public void quickSort(Integer[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(Integer[] array, int left, int right) {
        if (left + 10 <= right) {
            // 选择一个基准值，left right center由小到大排序后取中间值
            int pivot = median3(array, left, right);
            System.out.println("pivot: " + Arrays.asList(array));
            System.out.println("pivot: " + pivot);

            int i = left;
            int j = right - 1;

            for (; ; ) {
                while (array[++i] < pivot) {
                }

                while (array[--j] > pivot) {
                }

                if (i < j) {
                    swap(array, i, j);
                    System.out.println(Arrays.asList(array));
                } else {
                    break;
                }
            }

            swap(array, i, right - 1);
            System.out.println(Arrays.asList(array));
            quickSort(array, left, i - 1);
            quickSort(array, i + 1, right);
        } else {
            insertionSort(array, left, right);
        }
    }

    private void insertionSort(Integer[] array, int left, int right) {
        int j;
        for (int i = left; i <= right; i++) {
            Integer temp = array[i];
            for (j = i; j > 0 && temp < array[j - 1]; j--) {
                array[j] = array[j - 1];
            }
            array[j] = temp;
        }
    }

    private int median3(Integer[] array, int left, int right) {
        int center = (left + right) / 2;
        if (array[left] > array[center]) {
            swap(array, left, center);
            System.out.println(Arrays.asList(array));
        }

        if (array[left] > array[right]) {
            swap(array, left, right);
            System.out.println(Arrays.asList(array));
        }

        if (array[center] > array[right]) {
            swap(array, center, right);
            System.out.println(Arrays.asList(array));
        }

        // 将center和right - 1交换下，left center right已经排序，center作为pivot，left + 1 和right -1 可以作为两端
        swap(array, center, right - 1);
        System.out.println(Arrays.asList(array));
        return array[right - 1];
    }

    private void swap(Integer[] array, int m, int n) {
        int temp = array[m];
        array[m] = array[n];
        array[n] = temp;
    }

    public static void main(String[] args) {
        Integer[] array = {8, 1, 4, 9, 6, 3, 5, 2, 7, 0, 10, 23, 21};
        System.out.println("origin: " + Arrays.asList(array));
        new QuickSort().quickSort(array);
        System.out.println("after: " + Arrays.asList(array));
    }
}
