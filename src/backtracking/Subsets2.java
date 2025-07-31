package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Subsets II
 *
 * You are given an array nums of integers, which may contain duplicates. Return all possible subsets.
 *
 * The solution must not contain duplicate subsets. You may return the solution in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,2,1]
 * Output: [[],[1],[1,2],[1,1],[1,2,1],[2]]
 *
 * Example 2:
 *
 * Input: nums = [7,7]
 * Output: [[],[7], [7,7]]
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 11
 * 2. -20 <= nums[i] <= 20
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(n * (2^n)) time and O(n) space, where n is the size of the
 * input array.
 */
class Subsets2 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> allSubsets = new ArrayList<>();
        Arrays.sort(nums);
        subsetsWithDupInternal(nums, 0, new ArrayList<>(), allSubsets);
        return allSubsets;
    }

    public void subsetsWithDupInternal(int[] nums, int currentIndex, List<Integer> currentSubset, List<List<Integer>> allSubsets) {
        allSubsets.add(new ArrayList<>(currentSubset));
        for (int i = currentIndex; i < nums.length; i++) {
            if (i > currentIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            currentSubset.add(nums[i]);
            subsetsWithDupInternal(nums, i + 1, currentSubset, allSubsets);
            currentSubset.remove(currentSubset.size() - 1);
        }
    }
}

class Subsets2Tester {

    public static void printExample(int[] nums, String exampleId) {
        Subsets2 subsets2 = new Subsets2();
        System.out.println("subsetsWithDup" + exampleId + "=" + subsets2.subsetsWithDup(nums));
    }

    public static void main(String[] args) {
        printExample(new int[]{1, 2, 1}, "1");
        printExample(new int[]{7, 7}, "2");
    }
}
