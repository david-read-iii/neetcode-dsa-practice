package arraysandhashing;

import java.util.HashMap;

/*
 * Two Sum
 * Given an array of integers nums and an integer target, return the indices i and j such that
 * nums[i] + nums[j] == target and i != j.
 *
 * You may assume that every input has exactly one pair of indices i and j that satisfy the condition.
 *
 * Return the answer with the smaller index first.
 *
 * Example 1:
 * Input:
 * nums = [3,4,5,6], target = 7
 * Output: [0,1]
 * Explanation: nums[0] + nums[1] == 7, so we return [0, 1].
 *
 * Example 2:
 * Input: nums = [4,5,6], target = 10
 * Output: [0,2]
 *
 * Example 3:
 * Input: nums = [5,5], target = 10
 * Output: [0,1]
 *
 * Constraints:
 * 2 <= nums.length <= 1000
 * -10,000,000 <= nums[i] <= 10,000,000
 * -10,000,000 <= target <= 10,000,000
 *
 * Recommended Time & Space Complexity
 * You should aim for a solution with O(n) time and O(n) space, where n is the size of the input array.
 */
public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i], i);
        }

        for (int j = 0; j < nums.length; j++) {
            int difference = target - nums[j]; // value of nums[i] needed
            Integer indexOfDifference = hashMap.get(difference); // index of nums[i] needed
            if (indexOfDifference != null && indexOfDifference != j) {
                return new int[]{j, indexOfDifference};
            }
        }

        return null;
    }

    public static int[] twoSumInefficient2(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sumOfIAndJValues = nums[i] + nums[j];
                if (sumOfIAndJValues == target) {
                    if (nums[i] < nums[j]) {
                        return new int[]{nums[i], nums[j]};
                    } else {
                        return new int[]{nums[j], nums[i]};
                    }
                }
            }
        }

        return null;
    }

    // Uses O(n + n) time complexity?
    // Uses O(1) space complexity.
    public static int[] twoSumInefficient1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sumOfIAndJValues = nums[i] + nums[j];
                if (sumOfIAndJValues == target) {
                    if (nums[i] < nums[j]) {
                        return new int[]{nums[i], nums[j]};
                    } else {
                        return new int[]{nums[j], nums[i]};
                    }
                }
            }
        }

        return null;
    }

    public static String printArray(int[] nums) {
        if (nums == null) {
            return "null";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < nums.length; i++) {
            stringBuilder.append(nums[i]);
            if (i != nums.length - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        // Example 1
        int[] nums1 = new int[]{3, 4, 5, 6};
        int target1 = 7;
        int[] output1 = twoSum(nums1, target1);
        System.out.println("output1=" + printArray(output1));

        // Example 2
        int[] nums2 = new int[]{4, 5, 6};
        int target2 = 10;
        int[] output2 = twoSum(nums2, target2);
        System.out.println("output2=" + printArray(output2));

        // Example 3
        int[] nums3 = new int[]{5, 5};
        int target3 = 10;
        int[] output3 = twoSum(nums3, target3);
        System.out.println("output3=" + printArray(output3));

//        // Example 1
//        int[] nums1 = new int[]{3, 4, 5, 6};
//        int target1 = 7;
//        int[] output1 = twoSumInefficient2(nums1, target1);
//        System.out.println("output1=" + printArray(output1));
//
//        // Example 2
//        int[] nums2 = new int[]{4, 5, 6};
//        int target2 = 10;
//        int[] output2 = twoSumInefficient2(nums2, target2);
//        System.out.println("output2=" + printArray(output2));
//
//        // Example 3
//        int[] nums3 = new int[]{5, 5};
//        int target3 = 10;
//        int[] output3 = twoSumInefficient2(nums3, target3);
//        System.out.println("output3=" + printArray(output3));

//        // Example 1
//        int[] nums1 = new int[]{3, 4, 5, 6};
//        int target1 = 7;
//        int[] output1 = twoSumInefficient1(nums1, target1);
//        System.out.println("output1=" + printArray(output1));
//
//        // Example 2
//        int[] nums2 = new int[]{4, 5, 6};
//        int target2 = 10;
//        int[] output2 = twoSumInefficient1(nums2, target2);
//        System.out.println("output2=" + printArray(output2));
//
//        // Example 3
//        int[] nums3 = new int[]{5, 5};
//        int target3 = 10;
//        int[] output3 = twoSumInefficient1(nums3, target3);
//        System.out.println("output3=" + printArray(output3));
    }
}
