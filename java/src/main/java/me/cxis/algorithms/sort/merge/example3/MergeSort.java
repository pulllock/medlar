package me.cxis.algorithms.sort.merge.example3;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {

    /**
     * 归并排序
     * 归并排序采用分治思想（divide and conquer），先将
     * 整个序列递归分成小的序列，小的序列之间进行排序，并合并
     * 成稍微大一点的序列，稍微大一点的序列继续排序并合并成
     * 更大的序列，直到排序完成
     *
     * 首先是分，将大序列进行递归分成小序列，然后是小序列之间
     * 进行排序合并成稍微大的序列。
     * @param source
     */
    public void mergeSort(Integer[] source) {
        // 排序需要使用到一个辅助数组，排序前直接分配好，
        // 避免在排序过程中频繁进行空间的申请
        Integer[] temp = new Integer[source.length];

        // 开始进行分以及排序合并
        mergeSort(source, 0, source.length - 1, temp);
    }

    /**
     * 递归进行分，然后进行排序并合并
     * @param source
     * @param left
     * @param right
     * @param temp
     */
    private void mergeSort(Integer[] source, int left, int right, Integer[] temp) {
        if (left < right) {
            // 将大序列进行分，递归分成小序列
            int mid = (left + right) / 2;

            // 左边子序列进行分治
            mergeSort(source, left, mid, temp);

            // 右边子序列进行分治
            mergeSort(source, mid + 1, right, temp);

            // 将两个有序的子序列进行合并，需要将两个子序列中的元素进行排序后合并到一个序列中
            merge(source, left, mid, right, temp);
        }
    }

    /**
     * 将两个子序列进行排序、合并成一个序列
     * 序列1：从left到mid，序列2：从mid + 1到right
     * 序列1和序列2有序，将两个序列排序后合并到temp中，
     * 最后将temp复制到原序列中
     * @param source
     * @param left
     * @param mid
     * @param right
     * @param temp
     */
    private void merge(Integer[] source, int left, int mid, int right, Integer[] temp) {
        // 左边序列开始位置
        int i = left;

        // 右边序列开始位置
        int j = mid + 1;

        // 指向临时数组中的元素位置
        int t = 0;

        // 遍历左右两个序列
        while (i <= mid && j <= right) {
            // 这里示例的是从小到大排序的
            // 如果左边序列的元素比右边序列的元素小或者两者相等，将左边序列元素复制到临时数组中
            if (source[i] <= source[j]) {
                // 左边序列元素放到临时数组中
                temp[t++] = source[i++];
            }

            // 如果左边序列元素比右边序列元素大，需要将右边序列元素复制到临时数组中去
            else {
                temp[t++] = source[j++];
            }
        }

        // 这样进行排序并合并后，可能会产生两种情况：
        // 1.左边有剩余元素，右边遍历完了，此时左边剩余元素是最大的，直接复制到临时数组中去
        // 2.右边有剩余元素，左边遍历完了，此时右边剩余元素是最大的，直接复制到临时数组中去

        // 左边元素如果有剩余元素，右边没有剩余元素的话，说明左边剩余元素都是最大的
        // 将左边剩余元素直接复制到临时数组后面
        while (i <= mid) {
            temp[t++] = source[i++];
        }

        // 右边元素如果有剩余元素，左边没有剩余元素的话，说明右边剩余元素都是最大的
        // 将右边剩余元素直接复制到临时数组后面
        while (j <= right) {
            temp[t++] = source[j++];
        }

        // 经过上面步骤之后，temp中的元素是排好序的，元素位置0~t
        // 将temp中的元素复制到原数组中去，复制的目标位置是left~right

        // t复位
        t = 0;
        while (left <= right) {
            source[left++] = temp[t++];
        }
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[] {38, 55, 65, 97, 27, 76, 27, 13, 19};
        System.out.println("before: " + Arrays.asList(array));
        new MergeSort().mergeSort(array);
        System.out.println("after: " + Arrays.asList(array));
    }
}
