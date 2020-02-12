package me.cxis.algorithms.sort.quick.example2;

import java.util.Arrays;

public class QuickSort {

    public void quickSort(Integer[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(Integer[] array, int left, int right) {
        if (left < right) {
            // 最左边的作为基准值
            int pivot = array[left];
            int i = left;
            int j = right;
            while (i < j) {
                while (i < j && array[j] > pivot) {
                    j--;
                }

                if (i < j) {
                    array[i] = array[j];
                    i++;
                }

                while (i < j && array[i] < pivot) {
                    i++;
                }

                if (i < j) {
                    array[j] = array[i];
                    j--;
                }
            }

            array[i] = pivot;
            quickSort(array, left, i - 1);
            quickSort(array, i + 1, right);
        }
    }

    public static void main(String[] args) {
        Integer[] array = {0, 47, 29, 71, 99, 78, 19, 24};
        new QuickSort().quickSort(array);
        System.out.println(Arrays.asList(array));
    }
}
