package me.cxis.algorithms.sort.selection.simple.example1;

import java.util.Arrays;

public class SimpleSelectionSort {

    /**
     * 从小到大
     * @param array
     */
    public void simpleSelectionSort(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            // 找到最小元素的下标
            for (int j = i + 1; j < array.length; j++) {
                if (array[min] > array[j]) {
                    min = j;
                }
            }

            if (min != i) {
                // 将最小元素和i位置交换
                Integer temp = array[i];
                array[i] = array[min];
                array[min] = temp;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] array = {8, 3, 6, 2, 4, 5, 7, 1, 9, 0};
        System.out.println("before: " + Arrays.asList(array));
        SimpleSelectionSort sort = new SimpleSelectionSort();
        sort.simpleSelectionSort(array);
        System.out.println("after: " + Arrays.asList(array));
    }
}
