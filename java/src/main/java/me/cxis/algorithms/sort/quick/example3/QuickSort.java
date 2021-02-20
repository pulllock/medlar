package me.cxis.algorithms.sort.quick.example3;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {

    /**
     * 快速排序
     * 赋值填充方式，一端挖坑一端填充
     * 从右边找比基准小的元素，找到后将该元素填到基准元素所在位置，
     * 然后从左边找比基准大的元素，找到后填充到刚才的比基准元素小的那个元素位置上
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
                // 从右边开始找，找比基准小的元素
                while (i < j && source[j] > pivot) {
                    j--;
                }

                if (i < j) {
                    // 将比基准元素小的这个元素填充到左边空缺位置（基准元素位置或者已经交换过元素的位置）
                    source[i] = source[j];
                    i++;
                }

                // 右边找到比基准小的元素并交换后，开始从左到右找比基准大的元素
                while (i < j && source[i] < pivot) {
                    i++;
                }

                if (i < j) {
                    // 比基准元素大的元素，放到右边交换过元素的位置
                    source[j] = source[i];
                    j--;
                }
            }

            // i == j表示i、j相交，此时基准元素的位置就是这个相交位置
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
