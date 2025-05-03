package twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 3Sum
 *
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] where
 * nums[i] + nums[j] + nums[k] == 0, and the indices i, j and k are all distinct.
 *
 * The output should not contain any duplicate triplets. You may return the output and the triplets in any order.
 *
 * Example 1:
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 *
 * Example 2:
 *
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 *
 * Example 3:
 *
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 *
 * Constraints:
 *
 * 3 <= nums.length <= 1000
 * -10^5 <= nums[i] <= 10^5
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n^2) time and O(1) space, where n is the size of the input array.
 */
public class ThreeSum {

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (!checkForDuplicateNeighbor(i, nums)) {
                List<List<Integer>> jAndKPairs = findJAndKPairs(i, nums);
                for (List<Integer> jAndKPair : jAndKPairs) {
                    jAndKPair.add(0, nums[i]);
                    result.add(jAndKPair); // Now includes nums[i] too.
                }
            }
        }
        return result;
    }

    public static boolean checkForDuplicateNeighbor(int i, int[] nums) {
        if (i - 1 < 0) {
            return false;
        }
        return nums[i - 1] == nums[i];
    }

    public static List<List<Integer>> findJAndKPairs(int i, int[] nums) {
        List<List<Integer>> jAndKPairs = new ArrayList<>();
        int j = i + 1;
        int k = nums.length - 1;
        int target = -nums[i]; // -nums[i] = nums[j] + nums[k]

        while (j != k) {
            System.out.println("i=" + i + " j=" + j + " k=" + k);
            int sum = nums[j] + nums[k];
            if (sum == target && jIsNotADuplicate(i, j, nums) && kIsNotADuplicate(k, nums)) {
                List<Integer> pair = new ArrayList<>();
                pair.add(nums[j]);
                pair.add(nums[k]);
                jAndKPairs.add(pair);
                j++;
            } else if (sum < target) {
                j++;
            } else { // sum > target
                k--;
            }
        }

        return jAndKPairs;
    }

    public static boolean jIsNotADuplicate(int i, int j, int[] nums) {
        if (i + 1 == j) {
            return true; // j is one right of i, so not a duplicate.
        } else {
            return nums[j] != nums[j - 1];
        }
    }

    public static boolean kIsNotADuplicate(int k, int[] nums) {
        if (k == nums.length - 1) {
            return true; // k is the end of the array, so not a duplicate.
        } else {
            return nums[k] != nums[k + 1];
        }
    }

    public static void printExample(int[] nums, String exampleId) {
        System.out.println("threeSum" + exampleId + "=" + threeSum(nums));
    }

    public static void main(String[] args) {
        printExample(new int[]{-1, 0, 1, 2, -1, -4}, "1");
        printExample(new int[]{0, 1, 1}, "2");
        printExample(new int[]{0, 0, 0}, "3");
    }
}
