package me.cxis.algorithms.search.binarysearch.example;

/**
 * 二分查找（折半查找）
 *
 * 针对有序数据，先确定待查找记录的范围，然后逐步缩小范围直到查找到或者找不到该记录
 *
 * 需要依赖数组，利用下标随机访问元素
 * 需要是有序的数据
 */
public class BinarySearch {

    public boolean binarySearch(Integer[] array, Integer data) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int middle = (low + high) / 2;
            if (data == array[middle]) {
                return true;
            } else if (data < array[middle]) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return false;
    }

    public boolean binarySearch1(Integer[] array, int low, int high, Integer data) {
        if (low > high) {
            return false;
        }

        int middle = (low + high) / 2;
        if (array[middle] == data) {
            return true;
        } else if (array[middle] > data) {
            return binarySearch1(array, low, middle - 1, data);
        } else {
            return binarySearch1(array, middle + 1, high, data);
        }
    }

    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        BinarySearch binarySearch = new BinarySearch();
        System.out.println(binarySearch.binarySearch(array, 5));
        System.out.println(binarySearch.binarySearch(array, 3));
        System.out.println(binarySearch.binarySearch(array, 10));
        System.out.println("-------------");
        System.out.println(binarySearch.binarySearch1(array, 0, array.length - 1, 5));
        System.out.println(binarySearch.binarySearch1(array, 0, array.length - 1, 3));
        System.out.println(binarySearch.binarySearch1(array, 0, array.length - 1, 10));
    }
}
