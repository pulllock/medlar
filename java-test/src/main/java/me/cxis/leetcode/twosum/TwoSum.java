package me.cxis.leetcode.twosum;

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

    public static void main(String[] args) {
        int[] nums = {1,2,4,6,3};
        int target = 8;
        for(int i : twoSum(nums,target)){
            System.out.println(i);
        }
    }
}
