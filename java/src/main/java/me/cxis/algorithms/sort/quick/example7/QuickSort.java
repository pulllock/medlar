package me.cxis.algorithms.sort.quick.example7;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {

    /**
     * 快速排序
     * 三分双向扫描
     * 将序列分为：小于基准序列 + 等于基准序列 + 待排序序列 + 大于基准序列
     * 需要三个指针，i指向等于基准序列的最左边元素，k指向待排序序列的最左边，j指向待排序序列的最右边
     * @param source 待排序数组
     */
    public void quickSort(Integer[] source) {
        sort(source, 0, source.length - 1);
    }

    private void sort(Integer[] source, int begin, int end) {
        if (begin < end) {
            Integer pivot = source[begin];

            int i = begin;
            int j = end;
            int k = begin + 1;
            outer: while (k <= j) {
                // 比基准小
                if (source[k] < pivot) {
                    swap(source, i, k);
                    i++;
                    k++;
                }
                // 比基准大
                else if (source[k] > pivot) {
                    // j向左扫描，找到比pivot小的或者等于pivot的元素
                    while (source[j] > pivot) {
                        j--;
                        // 说明待排元素全部比pivot大
                        if (j < k) {
                            break outer;
                        }
                    }

                    // 待排序中找到了比pivot的元素
                    if (source[j] < pivot) {
                        // 将这个元素和k交换
                        swap(source, j, k);
                        // k当前元素比pivot小，和i进行交换
                        swap(source, i, k);
                        i++;
                    }
                    // 和pivot相等，直接和k交换
                    else {
                        swap(source, j, k);
                    }

                    k++;
                    j--;
                }
                // 等于基准
                else {
                    k++;
                }
            }

            // 小于基准的序列，快速排序
            sort(source, begin, i - 1);

            // 大于基准的序列，快速排序
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
