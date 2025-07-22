package backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * Subsets
 *
 * Given an array nums of unique integers, return all possible subsets of nums.
 *
 * The solution set must not contain duplicate subsets. You may return the solution in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * Example 2:
 *
 * Input: nums = [7]
 * Output: [[],[7]]
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 10
 * 2. -10 <= nums[i] <= 10
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n * (2^n)) time and O(n) space, where n is the size of the input array.
 */
class Subsets {

    // Time complexity O(n * 2^n)
    // Space complexity O(n)
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        subsetsInternal(nums, new boolean[nums.length], result);
        return result;
    }

    public void subsetsInternal(int[] nums, boolean[] includeNums, List<List<Integer>> result) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (includeNums[i]) {
                list.add(nums[i]);
            }
        }
        result.add(list);
        boolean[] newIncludeNums = iterateIncludeNumsOrNull(includeNums);
        if (newIncludeNums != null) {
            subsetsInternal(nums, newIncludeNums, result);
        }
    }

    public boolean[] iterateIncludeNumsOrNull(boolean[] includeNums) {
        boolean[] returnIncludeNums = includeNums.clone();
        for (int i = returnIncludeNums.length - 1; i >= 0; i--) {
            if (returnIncludeNums[i] == false) {
                returnIncludeNums[i] = true;
                return returnIncludeNums;
            } else {
                returnIncludeNums[i] = false;
            }
        }
        return null;
    }
}

class SubsetsTester {

    public static void printExample(int[] nums, String exampleId) {
        Subsets subsets = new Subsets();
        System.out.println("subsets" + exampleId + "=" + subsets.subsets(nums));
    }

    public static void main(String[] args) {
        printExample(new int[]{1, 2, 3}, "1");
        printExample(new int[]{7}, "2");
    }
}
