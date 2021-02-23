package me.cxis.algorithms.sort.selection.example2;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectionSort {

    public void selectionSort(Integer[] source) {

        for (int i = 0; i < source.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < source.length; j++) {
                if (source[j] < source[min]) {
                    min = j;
                }
            }

            if (min != i) {
                Integer temp = source[i];
                source[i] = source[min];
                source[min] = temp;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[] {38, 55, 65, 97, 27, 76, 27, 13, 19};
        System.out.println("before: " + Arrays.asList(array));
        new SelectionSort().selectionSort(array);
        System.out.println("after: " + Arrays.asList(array));
    }
}
