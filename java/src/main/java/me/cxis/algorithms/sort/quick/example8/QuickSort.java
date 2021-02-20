package me.cxis.algorithms.sort.quick.example8;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {

    /**
     * 快速排序
     * 双轴快排
     * 取两个基准：基准1 pivot1和基准2 pivot2，并且pivot1 <= pivot2
     * 将序列分为：基准1 + 小于基准1的序列 + 大于等于基准1并且小于等于基准2的序列 + 待排序序列 + 大于基准2的序列 + 基准2
     * @param source 待排序数组
     */
    public void quickSort(Integer[] source) {
        sort(source, 0, source.length - 1);
    }

    private void sort(Integer[] source, int begin, int end) {
        if (begin < end) {
            // 确保基准1比基准2小
            if (source[begin] > source[end]) {
                swap(source, begin, end);
            }

            Integer pivot1 = source[begin];
            Integer pivot2 = source[end];

            int i = begin;
            int j = end;
            int k = begin + 1;
            outer: while (k < j) {
                // 比基准1小
                if (source[k] < pivot1) {
                    i++;
                    // 将k位置元素交换到小于基准1的序列中
                    swap(source, i, k);
                    k++;
                }
                // 小于等于基准2
                else if (source[k] <= pivot2) {
                    k++;
                }
                // 大于等于基准1或大于基准2
                else {
                    while (source[--j] > pivot2) {
                        if (j <= k) {
                            break outer;
                        }
                    }

                    // 小于基准1
                    if (source[j] < pivot1) {
                        swap(source, j, k);
                        i++;
                        swap(source, i, k);
                    }
                    // 等于基准1
                    else {
                        swap(source, j, k);
                    }

                    k++;
                }
            }

            // 将基准1和基准2交换到中间
            swap(source, begin, i);
            swap(source, end, j);

            // 小于基准1的序列，快速排序
            sort(source, begin, i - 1);

            // 大于等于基准1，小于等于基准2，快速排序
            sort(source, i + 1, j - 1);

            // 大于基准2的序列，快速排序
            sort(source, j + 1, end);
        }
    }

    private void swap(Integer[] source, int i, int j) {
        Integer temp = source[i];
        source[i] = source[j];
        source[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[] {38, 55, 65, 97, 27, 76, 27, 13, 19};
        System.out.println("before: " + Arrays.asList(array));
        new QuickSort().quickSort(array);
        System.out.println("after: " + Arrays.asList(array));
    }
}
