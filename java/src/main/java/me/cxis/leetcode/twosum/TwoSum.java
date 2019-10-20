package me.cxis.leetcode.twosum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cx on 7/13/16.
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution.
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 *
 */
public class TwoSum {

    /**
     * 两次循环遍历，比较暴力
     * 想法比较简单。
     * 时间复杂度O(n^2)
     * 空间复杂度O(1)
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {

        for( int i = 0 ; i <= nums.length;i++){
            for(int j = i + 1;j < nums.length;j++){
                if(nums[i] + nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    /**
     * 两遍哈希表，以空间换时间的方式
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int value2Search = target - nums[i];
            if (map.containsKey(value2Search) && map.get(value2Search) != i) {
                return new int[] {i, map.get(value2Search)};
            }
        }
        return null;
    }

    /**
     * 一遍哈希表
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int value2Search = target - nums[i];
            if (map.containsKey(value2Search)) {
                return new int[] {i, map.get(value2Search)};
            }
            map.put(nums[i], i);
        }

        return null;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,4,6,3};
        int target = 8;
        /*for(int i : twoSum(nums,target)){
            System.out.println(i);
        }*/

        System.out.println(Arrays.toString(twoSum(nums, target)));
        System.out.println(Arrays.toString(twoSum1(nums, target)));
        System.out.println(Arrays.toString(twoSum2(nums, target)));
    }
}
