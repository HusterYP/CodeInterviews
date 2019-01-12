package leetcode;

import java.util.HashMap;

/**
 * 题目:
 * 两数和: https://leetcode.com/problems/two-sum/
 * 解题链接: https://blog.csdn.net/C_calary/article/details/75052639
 */
public class TwoSum {
    public static void main(String[] args) {
        int nums[] = {3, 2, 4};
        int target = 6;
        int[] result = twoSum(nums, target);
        System.out.println(result[0] + "  " + result[1]);
    }

    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return null;
        }
        int result[] = {0, 0};
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int difference = target - nums[i];
            if (map.get(difference) != null) {
                result[0] = i;
                result[1] = map.get(difference);
                return result;
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
