package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Combination Sum
 *
 * You are given an array of distinct integers nums and a target integer target. Your task is to return a list of all
 * unique combinations of nums where the chosen numbers sum to target.
 *
 * The same number may be chosen from nums an unlimited number of times. Two combinations are the same if the frequency
 * of each of the chosen numbers is the same, otherwise they are different.
 *
 * You may return the combinations in any order and the order of the numbers in each combination can be in any order.
 *
 * Example 1:
 *
 * Input:
 * nums = [2,5,6,9]
 * target = 9
 * Output: [[2,2,5],[9]]
 * Explanation:
 * 2 + 2 + 5 = 9. We use 2 twice, and 5 once.
 * 9 = 9. We use 9 once.
 *
 * Example 2:
 *
 * Input:
 * nums = [3,4,5]
 * target = 16
 * Output: [[3,3,3,3,4],[3,3,5,5],[4,4,4,4],[3,4,4,5]]
 *
 * Example 3:
 *
 * Input:
 * nums = [3]
 * target = 5
 * Output: []
 *
 * Constraints:
 *
 * 1. All elements of nums are distinct.
 * 2. 1 <= nums.length <= 20
 * 3. 2 <= nums[i] <= 30
 * 4. 2 <= target <= 30
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(2^(t/m)) time and O(t/m) space, where t is the given target and m is the minimum
 * value in the given array.
 */
class CombinationSum {

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        combinationSumInternal(
                nums,
                target,
                new int[nums.length],
                0,
                0,
                result
        );
        return new ArrayList<>(result);
    }

    public void combinationSumInternal(int[] nums, int target, int[] frequencies, int currentSum, int startIndex, List<List<Integer>> result) {
        System.out.println("freq=" + Arrays.toString(frequencies) + " currentSum=" + currentSum + " startIndex=" + startIndex);
        if (currentSum < target) {
            for (int i = startIndex; i < frequencies.length; i++) {
                int newSum = nums[i] + currentSum;
                if (newSum <= target) {
                    int[] nextFrequencies = frequencies.clone();
                    nextFrequencies[i] = nextFrequencies[i] + 1;
                    combinationSumInternal(nums, target, nextFrequencies, newSum, i, result);
                }
            }
        } else if (currentSum == target) {
            addToResult(nums, frequencies, result);
        }
    }

    public void addToResult(int[] nums, int[] frequencies, List<List<Integer>> result) {
        List<Integer> newList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < frequencies[i]; j++) {
                newList.add(nums[i]);
            }
        }
        result.add(newList);
    }
}

class CombinationSumTester {

    public static void printExample(int[] nums, int target, String exampleId) {
        CombinationSum combinationSum = new CombinationSum();
        System.out.println("combinationSum" + exampleId + "=" + combinationSum.combinationSum(nums, target));
    }

    public static void main(String[] args) {
        printExample(new int[]{2, 5, 6, 9}, 9, "1");
        printExample(new int[]{3, 4, 5}, 16, "2");
        printExample(new int[]{3}, 5, "3");
    }
}
