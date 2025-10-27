package dynamicprogramming1d;

/*
 * Maximum Product Subarray
 *
 * Given an integer array nums, find a subarray that has the largest product within the array and return it.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * You can assume the output will fit into a 32-bit integer.
 *
 * Example 1:
 *
 * Input: nums = [1,2,-3,4]
 * Output: 4
 *
 * Example 2:
 *
 * Input: nums = [-2,-1]
 * Output: 2
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 1000
 * 2. -10 <= nums[i] <= 10
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n) time and O(1) space, where n is the size of the input array.
 */
class MaximumProductSubarray {

    // Time complexity O(n)
    // Space complexity O(1)
    public int maxProduct(int[] nums) {
        int currMax = nums[0];
        int currMin = nums[0];
        int globalMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int a = nums[i];
            int b = nums[i] * currMax;
            int c = nums[i] * currMin;
            currMax = max(a, b, c);
            currMin = min(a, b, c);
            globalMax = Math.max(globalMax, currMax);
        }
        return globalMax;
    }

    public int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    public int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}

class MaximumProductSubarrayTester {

    public static void printExample(int[] nums, String exampleId) {
        MaximumProductSubarray maximumProductSubarray = new MaximumProductSubarray();
        System.out.println("maxProduct" + exampleId + "=" + maximumProductSubarray.maxProduct(nums));
    }

    public static void main(String[] args) {
        printExample(new int[]{1, 2, -3, 4}, "1");
        printExample(new int[]{-2, -1}, "2");
    }
}
