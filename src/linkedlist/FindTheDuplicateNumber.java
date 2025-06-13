package linkedlist;

/*
 * Find the Duplicate Number
 *
 * You are given an array of integers nums containing n + 1 integers. Each integer in nums is in the range [1, n]
 * inclusive.
 *
 * Every integer appears exactly once, except for one integer which appears two or more times. Return the integer that
 * appears more than once.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,2,2]
 * Output: 2
 *
 * Example 2:
 *
 * Input: nums = [1,2,3,4,4]
 * Output: 4
 *
 * Follow-up: Can you solve the problem without modifying the array nums and using O(1) extra space?
 *
 * Constraints:
 *
 * 1. 1 <= n <= 10000
 * 2. nums.length == n + 1
 * 3. 1 <= nums[i] <= n
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n) time and O(1) space, where n is the size of the input array.
 */
class FindTheDuplicateNumber {

    // Time complexity O(n)
    // Space complexity O(1)
    public int findDuplicate(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            int occurrenceIndex = Math.abs(nums[i]) - 1;
            if (isOccurrenceAtIndex(nums, occurrenceIndex)) {
                return Math.abs(nums[i]);
            }
            nums[occurrenceIndex] *= -1;
        }

        return -1;
    }

    public boolean isOccurrenceAtIndex(int[] nums, int occurrenceIndex) {
        return nums[occurrenceIndex] < 0;
    }
}

class FindTheDuplicateNumberTester {

    public static void printExample(int[] nums, String exampleId) {
        FindTheDuplicateNumber findTheDuplicateNumber = new FindTheDuplicateNumber();
        System.out.println("findTheDuplicateNumber" + exampleId + "=" + findTheDuplicateNumber.findDuplicate(nums));
    }

    public static void main(String[] args) {
        printExample(new int[]{1, 2, 3, 2, 2}, "1");
        printExample(new int[]{1, 2, 3, 4, 4}, "2");
        printExample(new int[]{1, 3, 4, 2, 2}, "3");
    }
}
