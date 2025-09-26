package dynamicprogramming1d;

/*
 * House Robber II
 *
 * You are given an integer array nums where nums[i] represents the amount of money the ith house has. The houses are
 * arranged in a circle, i.e. the first house and the last house are neighbors.
 *
 * You are planning to rob money from the houses, but you cannot rob two adjacent houses because the security system
 * will automatically alert the police if two adjacent houses were both broken into.
 *
 * Return the maximum amount of money you can rob without alerting the police.
 *
 * Example 1:
 *
 * Input: nums = [3,4,3]
 * Output: 4
 * Explanation: You cannot rob nums[0] + nums[2] = 6 because nums[0] and nums[2] are adjacent houses. The maximum you
 * can rob is nums[1] = 4.
 *
 * Example 2:
 *
 * Input: nums = [2,9,8,3,6]
 * Output: 15
 * Explanation: You cannot rob nums[0] + nums[2] + nums[4] = 16 because nums[0] and nums[4] are adjacent houses. The
 * maximum you can rob is nums[1] + nums[4] = 15.
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
class HouseRobber2 {

    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp1 = getDpArray(nums, 0, nums.length - 2);
        int[] dp2 = getDpArray(nums, 1, nums.length - 1);
        return Math.max(dp1[dp1.length - 1], dp2[dp2.length - 1]);
    }

    private int[] getDpArray(int[] nums, int startIndex, int endIndex) {
        int[] dp = new int[endIndex - startIndex + 1];
        for (int i = 0; i < dp.length; i++) {
            int numIndex = i + startIndex;
            int valueIfSkip = i - 1 >= 0
                    ? dp[i - 1]
                    : 0;
            int valueIfRob = i - 2 >= 0
                    ? dp[i - 2] + nums[numIndex]
                    : nums[numIndex];
            dp[i] = Math.max(valueIfSkip, valueIfRob);
        }
        return dp;
    }
}

class HouseRobber2Tester {

    public static void printExample(int[] nums, String exampleId) {
        HouseRobber2 houseRobber2 = new HouseRobber2();
        System.out.println("rob" + exampleId + "=" + houseRobber2.rob(nums));
    }

    public static void main(String[] args) {
        printExample(new int[]{3, 4, 3}, "1");
        printExample(new int[]{2, 9, 8, 3, 6}, "2");
        printExample(new int[]{1}, "3");
        printExample(new int[]{1, 2}, "4");
    }
}
