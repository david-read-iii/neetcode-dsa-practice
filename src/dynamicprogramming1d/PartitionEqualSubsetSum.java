package dynamicprogramming1d;

/*
 * Partition Equal Subset Sum
 *
 * You are given an array of positive integers nums.
 *
 * Return true if you can partition the array into two subsets, subset1 and subset2 where sum(subset1) == sum(subset2).
 * Otherwise, return false.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: true
 * Explanation: The array can be partitioned as [1, 4] and [2, 3].
 *
 * Example 2:
 *
 * Input: nums = [1,2,3,4,5]
 * Output: false
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 100
 * 2. 1 <= nums[i] <= 50
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(n * t) time and O(n * t) space, where n is the size of the
 * input array and t is half the sum of the array elements.
 */
class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // Odd sum means nums can't be partitioned into two subsets of equal sum.
        if (sum % 2 != 0) {
            return false;
        }
        int halfSum = sum / 2;

        // dp[i] means is there a possible subset in nums that sums to i.
        boolean[] dp = new boolean[halfSum + 1];

        // Any subset will trivially sum to 0.
        dp[0] = true;

        for (int num : nums) {
            // Add num to any existing sum already found for this pass.
            for (int i = dp.length - 1; i >= 0; i--) {
                if (dp[i] && i + num <= halfSum) {
                    dp[i + num] = true;
                }
            }
        }

        return dp[halfSum];
    }
}

class PartitionEqualSubsetSumTester {

    public static void printExample(int[] nums, String exampleId) {
        PartitionEqualSubsetSum partitionEqualSubsetSum = new PartitionEqualSubsetSum();
        System.out.println("canPartition" + exampleId + "=" + partitionEqualSubsetSum.canPartition(nums));
    }

    public static void main(String[] args) {
        printExample(new int[]{1, 2, 3, 4}, "1");
        printExample(new int[]{1, 2, 3, 4, 5}, "2");
    }
}
