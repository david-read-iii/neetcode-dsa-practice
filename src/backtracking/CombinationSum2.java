package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Combination Sum II
 *
 * You are given an array of integers candidates, which may contain duplicates, and a target integer target. Your task
 * is to return a list of all unique combinations of candidates where the chosen numbers sum to target.
 *
 * Each element from candidates may be chosen at most once within a combination. The solution set must not contain
 * duplicate combinations.
 *
 * You may return the combinations in any order and the order of the numbers in each combination can be in any order.
 *
 * Example 1:
 *
 * Input: candidates = [9,2,2,4,6,1,5], target = 8
 * Output: [
 *   [1,2,5],
 *   [2,2,4],
 *   [2,6]
 * ]
 *
 * Example 2:
 *
 * Input: candidates = [1,2,3,4,5], target = 7
 * Output: [
 *   [1,2,4],
 *   [2,5],
 *   [3,4]
 * ]
 *
 * Constraints:
 *
 * 1. 1 <= candidates.length <= 100
 * 2. 1 <= candidates[i] <= 50
 * 3. 1 <= target <= 30
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n * (2^n)) time and O(n) space, where n is the size of the input array.
 */
class CombinationSum2 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        return null;
    }
}

class CombinationSum2Tester {

    public static void printExample(int[] candidates, int target, String exampleId) {
        CombinationSum2 combinationSum2 = new CombinationSum2();
        System.out.println("combinationSum2-" + exampleId + "=" + combinationSum2.combinationSum2(candidates, target));
    }

    public static void main(String[] args) {
        printExample(new int[]{9, 2, 2, 4, 6, 1, 5}, 8, "1");
        printExample(new int[]{1, 2, 3, 4, 5}, 7, "2");
    }
}
