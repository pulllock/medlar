package me.cxis.algorithms.sort.selection.example3;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort {

    /**
     * 堆排序
     * 1. 将无序序列构造成大顶堆
     * 2. 将堆顶元素和未排序序列最后一个元素进行交换
     * 3. 调整未排序序列的大顶堆后，继循环2、3步
     *
     * 结点索引为i，则左子结点索引为2i + 1，右子结点索引为2i + 2，
     * 父节点索引为(i - 1) / 2
     * 最后一个非叶结点索引为：(array.length / 2) - 1
     * @param source
     */
    public void heapSort(Integer[] source) {

        // 构造大顶堆，从最后一个非叶结点开始
        for (int i = (source.length >> 1) - 1; i >= 0; i--) {
            adjustHeap(source, i, source.length - 1);
        }

        for (int i = source.length - 1; i > 0; i--) {
            // 交换堆顶和最后一个元素
            swap(source, 0, i);

            // 调整剩余未排序序列的大顶堆
            adjustHeap(source, 0, i - 1);
        }

    }

    /**
     * 以start为根的子树调整成大顶堆
     * @param source
     * @param start
     * @param end
     */
    private void adjustHeap(Integer[] source, int start, int end) {
        Integer temp = source[start];

        // 从当前元素的左子结点开始遍历
        for (int i = 2 * start + 1; i <= end; i = 2 * i + 1) {
            // 如果左子结点比右子结点小，则将i指向右子结点
            if (i + 1 <= end && source[i] < source[i + 1]) {
                i++;
            }

            // 到这里i指向左右子结点中大的那个结点
            // 比较大的结点和父结点进行比较，如果比父结点大，需要放到父结点位置上去
            if (source[i] > temp) {
                // 比父结点大的元素，放到父结点位置上
                source[start] = source[i];
                start = i;
            } else {
                break;
            }
        }

        // 遍历完后，将temp元素放到最终位置
        source[start] = temp;
    }

    private void swap(Integer[] source, int i, int j) {
        Integer temp = source[i];
        source[i] = source[j];
        source[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[] {38, 55, 65, 97, 27, 76, 27, 13, 19};
        System.out.println("before: " + Arrays.asList(array));
        new HeapSort().heapSort(array);
        System.out.println("after: " + Arrays.asList(array));
    }
}
