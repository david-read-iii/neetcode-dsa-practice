package dynamicprogramming1d;

/*
 * House Robber
 *
 * You are given an integer array nums where nums[i] represents the amount of money the ith house has. The houses are
 * arranged in a straight line, i.e. the ith house is the neighbor of the (i-1)th and (i+1)th house.
 *
 * You are planning to rob money from the houses, but you cannot rob two adjacent houses because the security system
 * will automatically alert the police if two adjacent houses were both broken into.
 *
 * Return the maximum amount of money you can rob without alerting the police.
 *
 * Example 1:
 *
 * Input: nums = [1,1,3,3]
 * Output: 4
 * Explanation: nums[0] + nums[2] = 1 + 3 = 4.
 *
 * Example 2:
 *
 * Input: nums = [2,9,8,3,6]
 * Output: 16
 * Explanation: nums[0] + nums[2] + nums[4] = 2 + 8 + 6 = 16.
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 100
 * 2. 0 <= nums[i] <= 100
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(n) time and O(n) space, where n is the number of houses.
 */
class HouseRobber {

    // Time complexity O(n)
    // Space complexity O(n)
    public int rob(int[] nums) {

        // dp[i] is the max money I can rob from houses 0..i
        int[] dp = new int[nums.length];

        for (int i = 0; i < dp.length; i++) {
            int valueIfSkip = i - 1 >= 0
                    ? dp[i - 1]
                    : 0;
            int valueIfRob = i - 2 >= 0
                    ? nums[i] + dp[i - 2]
                    : nums[i];
            dp[i] = Math.max(valueIfSkip, valueIfRob);
        }

        return dp[nums.length - 1];
    }
}

class HouseRobberTester {

    public static void printExample(int[] nums, String exampleId) {
        HouseRobber houseRobber = new HouseRobber();
        System.out.println("rob" + exampleId + "=" + houseRobber.rob(nums));
    }

    public static void main(String[] args) {
        printExample(new int[]{1, 1, 3, 3}, "1");
        printExample(new int[]{2, 9, 8, 3, 6}, "2");
        printExample(new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 40, 42, 44, 46, 48, 50, 52, 54, 56, 58, 60, 62, 64, 66, 68, 70, 72, 74, 76, 78, 80, 82, 84, 86, 88, 90, 92, 94, 96, 98, 100, 99, 97, 95, 93, 91, 89, 87, 85, 83, 81, 79, 77, 75, 73, 71, 69, 67, 65, 63, 61, 59, 57, 55, 53, 51, 49, 47, 45, 43, 41, 39, 37, 35, 33, 31, 29, 27, 25, 23}, "3");
    }
}
