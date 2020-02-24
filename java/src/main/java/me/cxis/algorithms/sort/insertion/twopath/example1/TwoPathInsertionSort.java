package me.cxis.algorithms.sort.insertion.twopath.example1;

import java.util.Arrays;

/**
 * 2-路插入排序
 */
public class TwoPathInsertionSort {

    public void twoPathInsertionSort(Integer[] array) {
        // 二路插入排序的开始，最小元素
        int first = 0;

        // 二路插入排序的结尾，最大元素
        int last = 0;

        // 数组长度
        int n = array.length;

        // 中间数组，想象成一个环形数组
        Integer[] back = new Integer[n];

        back[0] = array[0];

        for (int i = 1; i < array.length; i++) {
            // 放到first的左边
            if (array[i] < back[first]) {
                // 数据在first左边，环形数组first向左移动一位的计算
                first = (first - 1 + n) % n;
                back[first] = array[i];
            } else if (array[i] >= back[last]) {
                // 数据在last右边，环形数组last向右移动一位
                last = (last + 1 + n) % n;
                back[last] = array[i];
            } else {
                // 在first和last之间
                // 使用折半插入
                int low = first;
                int high = last;
                while (low != high) {
                    // low high 之间元素个数
                    int d = (high - low + n) % n;
                    // 中间元素
                    int mid = (low + d / 2) %n;

                    if (array[i] < back[mid]) {
                        high = mid;
                    } else {
                        low = (mid + 1) % n;
                    }
                }

                for (int k = last + 1; k != low; k = (k - 1 + n) % n) {
                    back[k] = back[(k - 1 + n) % n];
                }
                back[low] = array[i];
                last = (last + 1 + n) % n;
            }
        }

        // 复制到原数组
        for (int i = 0; i < n; i++) {
            array[i] = back[(i + first) % n];
        }

        back = null;
    }

    public static void main(String[] args) {
        Integer[] array = {8, 3, 6, 2, 4, 5, 7, 1, 9, 0};
        System.out.println("before: " + Arrays.asList(array));
        TwoPathInsertionSort sort = new TwoPathInsertionSort();
        sort.twoPathInsertionSort(array);
        System.out.println("after: " + Arrays.asList(array));
    }
}
