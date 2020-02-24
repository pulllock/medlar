package me.cxis.algorithms.sort.bubble.example1;

import java.util.Arrays;

public class BubbleSort {

    /**
     * 小到大
     * @param array
     */
    public void bubbleSort(Integer[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] array = {8, 3, 6, 2, 4, 5, 7, 1, 9, 0};
        System.out.println("before: " + Arrays.asList(array));
        BubbleSort sort = new BubbleSort();
        sort.bubbleSort(array);
        System.out.println("after: " + Arrays.asList(array));
    }
}
