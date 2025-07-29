package backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * Permutations
 *
 * Given an array nums of unique integers, return all the possible permutations. You may return the answer in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Example 2:
 *
 * Input: nums = [7]
 * Output: [[7]]
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 6
 * 2. -10 <= nums[i] <= 10
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n * n!) time and O(n) space, where n is the size of the input array.
 */
class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> allResults = new ArrayList<>();
        permuteInternal(nums, new boolean[nums.length], new ArrayList<>(), allResults);
        return allResults;
    }

    public void permuteInternal(int[] nums, boolean[] isSelected, List<Integer> currentResult, List<List<Integer>> allResults) {
        if (currentResult.size() == nums.length) {
            allResults.add(new ArrayList<>(currentResult));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!isSelected[i]) {
                selectNum(i, nums, isSelected, currentResult);
                permuteInternal(nums, isSelected, currentResult, allResults);
                unselectNum(i, nums, isSelected, currentResult);
            }
        }
    }

    public void selectNum(int index, int[] nums, boolean[] isSelected, List<Integer> currentResult) {
        isSelected[index] = true;
        currentResult.add(nums[index]);
    }

    public void unselectNum(int index, int[] nums, boolean[] isSelected, List<Integer> currentResult) {
        isSelected[index] = false;
        currentResult.removeLast();
    }
}

class PermutationsTester {

    public static void printExample(int[] nums, String exampleId) {
        Permutations permutations = new Permutations();
        System.out.println("permute" + exampleId + "=" + permutations.permute(nums));
    }

    public static void main(String[] args) {
        printExample(new int[]{1, 2, 3}, "1");
        printExample(new int[]{7}, "2");
    }
}
