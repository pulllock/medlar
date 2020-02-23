package me.cxis.algorithms.sort.insertion.straight;

import java.util.Arrays;

/**
 * 直接插入排序
 */
public class StraightInsertionSort {

    public void straightInsertionSort(Integer[] array) {
        // 已排序元素下标
        int i;

        // j 待排序元素下标
        for (int j = 1; j < array.length; j++) {
            if (array[j] < array[j - 1]) {
                // 待排序的元素
                Integer temp = array[j];

                // 从已排序的最后往前比较
                for (i = j - 1; i >= 0 && array[i] > temp; i--) {
                    array[i + 1] = array[i];
                }

                array[i + 1] = temp;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] array = {8, 3, 6, 2, 4, 5, 7, 1, 9, 0};
        System.out.println("before: " + Arrays.asList(array));
        StraightInsertionSort sort = new StraightInsertionSort();
        sort.straightInsertionSort(array);
        System.out.println("after: " + Arrays.asList(array));
    }
}
