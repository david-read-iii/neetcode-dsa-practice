package binarysearch;

/*
 * Find Minimum in Rotated Sorted Array
 *
 * You are given an array of length n which was originally sorted in ascending order. It has now been rotated between 1
 * and n times. For example, the array nums = [1,2,3,4,5,6] might become:
 *
 * - [3,4,5,6,1,2] if it was rotated 4 times.
 * - [1,2,3,4,5,6] if it was rotated 6 times.
 *
 * Notice that rotating the array 4 times moves the last four elements of the array to the beginning. Rotating the array
 * 6 times produces the original array.
 *
 * Assuming all elements in the rotated sorted array nums are unique, return the minimum element of this array.
 *
 * A solution that runs in O(n) time is trivial, can you write an algorithm that runs in O(log n) time?
 *
 * Example 1:
 *
 * Input: nums = [3,4,5,6,1,2]
 * Output: 1
 *
 * Example 2:
 *
 * Input: nums = [4,5,0,1,2,3]
 * Output: 0
 *
 * Example 3:
 *
 * Input: nums = [4,5,6,7]
 * Output: 4
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 1000
 * 2. -1000 <= nums[i] <= 1000
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(logn) time and O(1) space, where n is the size of the input array.
 */
public class FindMinimumInRotatedSortedArray {

    // Time complexity O(log(n))
    // Space complexity O(log(n))
    public static int findMin(int[] nums) {
        return findMinInternal(nums, 0, nums.length - 1);
    }

    public static int findMinInternal(int[] nums, int l, int r) {
        int m = ((r - l) / 2) + l;

        if (isMinimum(nums, m)) {
            return nums[m];
        } else {
            SearchOption searchOption = getSearchOption(nums, l, m, r);
            if (searchOption == SearchOption.LEFT) {
                return findMinInternal(nums, l, m - 1);
            } else { // SearchOption.RIGHT
                return findMinInternal(nums, m + 1, r);
            }
        }
    }

    public static boolean isMinimum(int[] nums, int m) {
        if (nums.length == 1) {
            return true;
        }

        int prevIndex;
        if (m == 0) {
            prevIndex = nums.length - 1;
        } else {
            prevIndex = m - 1;
        }

        if (nums[prevIndex] > nums[m]) {
            return true;
        } else {
            return false;
        }
    }

    public static SearchOption getSearchOption(int[] nums, int l, int m, int r) {
        int leftL = l;
        int leftR;
        if (m - 1 < l) {
            leftR = m;
        } else {
            leftR = m - 1;
        }

        int rightL;
        if (m + 1 > r) {
            rightL = m;
        } else {
            rightL = m + 1;
        }
        int rightR = r;

        if (leftL != leftR && nums[leftL] > nums[leftR]) {
            return SearchOption.LEFT;
        } else if (rightL != rightR && nums[rightL] > nums[rightR]) {
            return SearchOption.RIGHT;
        } else if (nums[leftL] < nums[rightL]) {
            return SearchOption.LEFT;
        } else { // nums[leftL] >= nums[rightL]
            return SearchOption.RIGHT;
        }
    }

    public enum SearchOption {LEFT, RIGHT}

    public static void printExample(int[] nums, String exampleId) {
        System.out.println("findMin" + exampleId + "=" + findMin(nums));
    }

    public static void main(String[] args) {
        printExample(new int[]{3, 4, 5, 6, 1, 2}, "1");
        printExample(new int[]{4, 5, 0, 1, 2, 3}, "2");
        printExample(new int[]{4, 5, 6, 7}, "3");
        printExample(new int[]{1}, "4");
        printExample(new int[]{5, 1, 2, 3, 4}, "5");
        printExample(new int[]{2, 3, 1}, "6");
        printExample(new int[]{1, 2, 3, 4, 5}, "7");
        printExample(new int[]{2, 3, 4, 5, 1}, "8");
    }
}
