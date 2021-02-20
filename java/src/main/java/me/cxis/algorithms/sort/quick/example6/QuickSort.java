package me.cxis.algorithms.sort.quick.example6;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {

    /**
     * 快速排序
     * 三分单向扫描
     * 将序列分为：小于基准序列 + 等于基准序列 + 待排序序列 + 大于基准序列
     * @param source 待排序数组
     */
    public void quickSort(Integer[] source) {
        sort(source, 0, source.length - 1);
    }

    private void sort(Integer[] source, int begin, int end) {
        // TODO https://blog.csdn.net/holmofy/article/details/71168530
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[] {38, 55, 65, 97, 27, 76, 27, 13, 19};
        System.out.println("before: " + Arrays.asList(array));
        new QuickSort().quickSort(array);
        System.out.println("after: " + Arrays.asList(array));
    }
}
