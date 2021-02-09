package me.cxis.algorithms.sort.insertion.straight.example3;

import java.util.Arrays;

/**
 * 直接插入排序
 */
public class InsertionSort {

    /**
     * 直接插入排序
     * @param source 待排序的数组
     */
    public void insertionSort(Integer[] source) {
        /**
         * 从索引位置位1的地方开始循环待排序数组，这里是将数组分为两部分：
         * 索引位置为0的是当做前半部分已经排序的序列，从索引位置为1的开始
         * 当做是后半部分未排序的序列。
         */
        for (int i = 1; i < source.length; i++) {
            // 从后半部分未排序的序列中取出第一个元素
            Integer temp = source[i];
            int j = 0;
            /**
             * 从已排序的最后一个元素开始往前遍历，并和从后半部分未排序的
             * 序列中取出的第一个元素source[i]进行比较，如果已排序的元素比未排
             * 序的元素大，就将已排序序列中这个元素往右边移动一位，接续往已排序的
             * 序列左边进行比较；如果遇到了已排序的元素比未排序的元素小或者相等，
             * 说明未排序这个元素找到了位置，就将这个未排序的元素插入到该位置处即可
             */
            for (j = i - 1; j >= 0 && source[j] > temp; j--) {
                source[j + 1] = source[j];
            }
            source[j + 1] = temp;
        }
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[] {3, 4, 6, 2, 3, 1, 4, 10, 48, 2, 3, 12, 14};
        new InsertionSort().insertionSort(array);
        System.out.println(Arrays.toString(array));
    }
}
