package binarysearch;

/*
 * Search in Rotated Sorted Array
 *
 * You are given an array of length n which was originally sorted in ascending order. It has now been rotated between 1
 * and n times. For example, the array nums = [1,2,3,4,5,6] might become:
 *
 * - [3,4,5,6,1,2] if it was rotated 4 times.
 * - [1,2,3,4,5,6] if it was rotated 6 times.
 *
 * Given the rotated sorted array nums and an integer target, return the index of target within nums, or -1 if it is not
 * present.
 *
 * You may assume all elements in the sorted rotated array nums are unique,
 *
 * A solution that runs in O(n) time is trivial, can you write an algorithm that runs in O(log n) time?
 *
 * Example 1:
 *
 * Input: nums = [3,4,5,6,1,2], target = 1
 * Output: 4
 *
 * Example 2:
 *
 * Input: nums = [3,5,6,0,1,2], target = 4
 * Output: -1
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 1000
 * 2. -1000 <= nums[i] <= 1000
 * 3. -1000 <= target <= 1000
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(logn) time and O(1) space, where n is the size of the input array.
 */
public class SearchInRotatedSortedArray {

    // Time complexity: O(log(n))
    // Space complexity: O(1)
    public static int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        while (l <= r) {
            int m = ((r - l) / 2) + l;

            if (nums[m] == target) {
                return m;
            } else if (isLeftSubArraySorted(nums, l, m)) {
                if (isTargetBetween(target, nums[l], nums[m])) {
                    r = m - 1; // search left of m
                } else {
                    l = m + 1; // search right of m
                }
            } else if (isRightSubArraySorted(nums, m, r)) {
                if (isTargetBetween(target, nums[m], nums[r])) {
                    l = m + 1; // search right of m
                } else {
                    r = m - 1; // search left of m
                }
            } else {
                l = m + 1;
            }
        }

        return -1;
    }

    public static boolean isLeftSubArraySorted(int[] nums, int l, int m) {
        return nums[l] <= nums[m];
    }

    public static boolean isRightSubArraySorted(int[] nums, int m, int r) {
        return nums[m] <= nums[r];
    }

    public static boolean isTargetBetween(int target, int i, int j) {
        return i <= target && j >= target;
    }

    public static void printExample(int[] nums, int target, String exampleId) {
        System.out.println("search" + exampleId + "=" + search(nums, target));
    }

    public static void main(String[] args) {
        printExample(new int[]{3, 4, 5, 6, 1, 2}, 1, "1");
        printExample(new int[]{3, 5, 6, 0, 1, 2}, 4, "2");
        printExample(new int[]{4, 5, 6, 7, 0, 1, 2}, 0, "3");
    }
}
