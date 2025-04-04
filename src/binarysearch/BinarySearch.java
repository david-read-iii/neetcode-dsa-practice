package binarysearch;

/*
 * Binary Search
 *
 * You are given an array of distinct integers nums, sorted in ascending order, and an integer target.
 *
 * Implement a function to search for target within nums. If it exists, then return its index, otherwise, return -1.
 *
 * Your solution must run in O(logn) time.
 *
 * Example 1:
 *
 * Input: nums = [-1,0,2,4,6,8], target = 4
 * Output: 3
 *
 * Example 2:
 *
 * Input: nums = [-1,0,2,4,6,8], target = 3
 * Output: -1
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10000.
 * -10000 < nums[i], target < 10000
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(logn) time and O(1) space, where n is the size of the input array.
 */
public class BinarySearch {

    public static int search(int[] nums, int target) {
        int leftIndex = 0;
        int rightIndex = nums.length - 1;
        int midIndex = getMidIndex(leftIndex, rightIndex);
        return searchInternal(nums, target, leftIndex, midIndex, rightIndex);
    }

    public static int searchInternal(int[] nums, int target, int leftIndex, int midIndex, int rightIndex) {
        if (getLength(leftIndex, rightIndex) < 1) {
            return -1;
        }
        if (nums[midIndex] == target) {
            return midIndex;
        } else if (target < nums[midIndex]) {
            int newLeftIndex = leftIndex;
            int newRightIndex = midIndex - 1;
            int newMidIndex = getMidIndex(newLeftIndex, newRightIndex);
            return searchInternal(nums, target, newLeftIndex, newMidIndex, newRightIndex);
        } else { // Assumed target > nums[midIndex]
            int newLeftIndex = midIndex + 1;
            int newRightIndex = rightIndex;
            int newMidIndex = getMidIndex(newLeftIndex, newRightIndex);
            return searchInternal(nums, target, newLeftIndex, newMidIndex, newRightIndex);
        }
    }

    public static int getMidIndex(int leftIndex, int rightIndex) {
        int length = getLength(leftIndex, rightIndex);
        int midIndex = length / 2;
        return midIndex + leftIndex;
    }

    public static int getLength(int leftIndex, int rightIndex) {
        return rightIndex - leftIndex + 1;
    }

    public static void printExample(int[] nums, int target, String exampleId) {
        int index = search(nums, target);
        System.out.println("index" + exampleId + "=" + index);
    }

    public static void main(String[] args) {
        printExample(new int[]{-1, 0, 2, 4, 6, 8}, 4, "1");
        printExample(new int[]{-1, 0, 2, 4, 6, 8}, 3, "2");
        printExample(new int[]{-1, 0, 3, 5, 9, 12}, 13, "3");
    }
}
