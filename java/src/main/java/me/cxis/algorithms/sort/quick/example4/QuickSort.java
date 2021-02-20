package me.cxis.algorithms.sort.quick.example4;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {

    /**
     * 快速排序
     * 两端扫描交换方式
     * 从左边开始扫描，找到一个比基准大的元素，接着从右边开始扫描，找到一个比基准小的元素，
     * 将这两个元素进行交换。
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
            int i = begin + 1;
            // 序列右边
            int j = end;
            while (i <= j) {
                // 从左边开始找，找比基准大的元素
                while (i <= j && source[i] < pivot) {
                    i++;
                }

                // 从左边找到了比基准大的元素之后，开始从右到左找比基准小或相等的元素
                while (i <= j && source[j] >= pivot) {
                    j--;
                }

                // 此时在左边找到了比基准大的元素，在右边找到了比基准小的元素
                if (i <= j) {
                    // 交换两个元素位置
                    Integer temp = source[j];
                    source[j] = source[i];
                    source[i] = temp;
                }
            }

            // i < j，说明交错了，将基准交换到中心位置
            Integer temp = source[begin];
            source[begin] = source[j];
            source[j] = temp;

            // 比基准元素小的序列，左边序列，进行快排
            sort(source, begin, j - 1);
            // 比基准元素大的序列，右边序列，进行快排
            sort(source, j + 1, end);
        }
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[] {38, 55, 65, 97, 27, 76, 27, 13, 19};
        System.out.println("before: " + Arrays.asList(array));
        new QuickSort().quickSort(array);
        System.out.println("after: " + Arrays.asList(array));
    }
}
