package me.cxis.algorithms.sort.quick.example5;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {

    /**
     * 快速排序
     * 单向扫描划分
     * 将序列分成：基准 + 比基准小的序列 + 比基准大的序列 + 待排序列
     * 扫描比基准大的序列，如果元素比基准小，则将这个元素和比基准大的序列的最左端交换，
     * 并让这个最左端元素变成比基准小的序列的最右端
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
            int j = begin + 1;

            while (j <= end) {
                // 右边序列元素比基准小，需要将这个元素和右边序列最左边元素进行交换，
                // 并将右边元素最左边元素这个位置变为左边序列的元素
                if (source[j] < pivot) {
                    i++;
                    Integer temp = source[j];
                    source[j] = source[i];
                    source[i] = temp;
                }
                j++;
            }

            // 扫描完后，现在变成了：基准元素+比基准小的序列+比基准大的序列
            // 此时需要把基准元素和比基准小的序列的最右端元素进行交换，让基准元素变成中间
            source[begin] = source[i];
            source[i] = pivot;

            // 比基准元素小的序列，左边序列，进行快排
            sort(source, begin, i - 1);
            // 比基准元素大的序列，右边序列，进行快排
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
