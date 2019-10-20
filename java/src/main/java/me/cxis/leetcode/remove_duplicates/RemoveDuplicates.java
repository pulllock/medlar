package me.cxis.leetcode.remove_duplicates;

import java.util.Arrays;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 不需要考虑数组中超出新长度后面的元素
 */
public class RemoveDuplicates {

    /**
     * 双指针法
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    public static int remove(int[] nums) {
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(String.format(
                "after remove, nums length is: %d, nums: %s",
                remove(nums),
                Arrays.toString(nums)
        ));
    }
}
