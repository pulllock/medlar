package me.cxis.algorithms.sort.quick.example3;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {

    /**
     * 快速排序
     * @param source 待排序数组
     */
    public void quickSort(Integer[] source) {
        sort(source, 0, source.length - 1);
    }

    private void sort(Integer[] source, int begin, int end) {
        if (begin < end) {
            // 基准值
            Integer pivot = source[begin];
            // 序列左边
            int i = begin;
            // 序列右边
            int j = end;
            while (i < j) {
                while (i < j && source[j] > pivot) {
                    j--;
                }

                if (i < j) {
                    source[i] = source[j];
                    i++;
                }

                while (i < j && source[i] < pivot) {
                    i++;
                }

                if (i < j) {
                    source[j] = source[i];
                    j--;
                }
            }

            source[i] = pivot;
            sort(source, begin, i - 1);
            sort(source, i + 1, end);
        }
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[] {38, 55, 65, 97, 27, 76, 27, 13, 19};
        System.out.println("before: " + Arrays.asList(array));
        new QuickSort().quickSort(array);
        System.out.println("after: " + Arrays.asList(array));
    }
}
