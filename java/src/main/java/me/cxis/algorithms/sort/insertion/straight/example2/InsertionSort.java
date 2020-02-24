package me.cxis.algorithms.sort.insertion.straight.example2;

import java.util.Arrays;

public class InsertionSort<T extends Comparable> {

    /**
     * 从小到大排序
     * 1 3 2 4
     * 排序后：
     * 1 2 3 4
     * @param array
     */
    public void insertionSort(T[] array) {
        int j;
        for (int i = 1; i < array.length; i++) {
            T temp = array[i];

            for (j = i; j > 0 && temp.compareTo(array[j - 1]) < 0; j--) {
                array[j] = array[j - 1];
            }

            array[j] = temp;
        }
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{1, 3, 2, 4};
        System.out.println("before: " + Arrays.asList(array).toString());
        InsertionSort<Integer> sort = new InsertionSort<>();
        sort.insertionSort(array);
        System.out.println("after: " + Arrays.asList(array).toString());
    }
}
