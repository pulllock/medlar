package me.cxis.algorithms.sort.insertion.pair;

import java.util.Arrays;

/**
 * 成对插入排序
 * 直接插入排序的优化算法，一次处理两个相邻的元素
 */
public class PairInsertionSort {

    public void pairInsertionSort(Integer[] source) {

        for (int i = 1; i < source.length - 1; i += 2) {
            Integer a1 = source[i];
            Integer a2 = source[i + 1];

            // 确保a1大于等于a2
            if (a1 < a2) {
                a1 = a2;
                a2 = source[i];
            }

            int j;
            // 先排序a1
            for (j = i - 1; j >= 0 && source[j] > a1; j--) {
                source[j + 2] = source[j];
            }

            source[j + 2] = a1;

            // 排序a2
            for (; j >= 0 && source[j] > a2; j--) {
                source[j + 1] = source[j];
            }

            source[j + 1] = a2;
        }

        // 如果数组的待排序序列是奇数个，还会剩余最后一个未排序，需要再进行最后一次排序
        Integer last = source[source.length - 1];
        int j;
        for (j = source.length - 2; j >= 0 && source[j] > last; j--) {
            source[j + 1] = source[j];
        }

        source[j + 1] = last;
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[] {3, 4, 6, 2, 3, 1, 4, 10, 48, 2, 3, 12, 14, 13};
        new PairInsertionSort().pairInsertionSort(array);
        System.out.println(Arrays.toString(array));
    }
}
