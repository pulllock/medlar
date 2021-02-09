package me.cxis.algorithms.sort.insertion.shell;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class ShellSort {

    public void shellSort(Integer[] source) {
        // 先确定增量，隔着相同增量的元素都在一个组里
        for (int gap = source.length >> 1; gap > 0; gap >>= 1) {
            // 每个组进行插入排序
            for (int i = gap; i < source.length; i++) {
                Integer temp = source[i];
                int j = 0;
                for (j = i; j >= gap && temp < source[j - gap] ; j = j - gap) {
                    source[j] = source[j - gap];
                }
                source[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[] {38, 55, 65, 97, 27, 76, 27, 13, 19};
        System.out.println("before: " + Arrays.asList(array));
        new ShellSort().shellSort(array);
        System.out.println("after: " + Arrays.asList(array));
    }
}
