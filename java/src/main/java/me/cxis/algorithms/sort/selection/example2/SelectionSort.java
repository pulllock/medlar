package me.cxis.algorithms.sort.selection.example2;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectionSort {

    /**
     * 直接选择排序
     * @param source 待排序序列
     */
    public void selectionSort(Integer[] source) {

        // 每次循环，选择一个最小元素
        for (int i = 0; i < source.length - 1; i++) {
            // 待排序第一个元素当做是最小值
            int min = i;
            // 从待排序第二个元素开始往后找比min小的元素
            for (int j = i + 1; j < source.length; j++) {
                // 找到了比min小的元素后，赋值给min
                if (source[j] < source[min]) {
                    min = j;
                }
            }

            // 一次循环后，将待排序第一个元素和min进行交换
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
