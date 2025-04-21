package twopointers;

import java.util.Arrays;

/*
 * Two Integer Sum II
 *
 * Given an array of integers numbers that is sorted in non-decreasing order.
 *
 * Return the indices (1-indexed) of two numbers, [index1, index2], such that they add up to a given target number
 * target and index1 < index2. Note that index1 and index2 cannot be equal, therefore you may not use the same element
 * twice.
 *
 * There will always be exactly one valid solution.
 *
 * Your solution must use O(1) additional space.
 *
 * Example 1:
 *
 * Input: numbers = [1,2,3,4], target = 3
 * Output: [1,2]
 * Explanation: The sum of 1 and 2 is 3. Since we are assuming a 1-indexed array, index1 = 1, index2 = 2. We return
 * [1, 2].
 *
 * Constraints:
 *
 * 2 <= numbers.length <= 1000
 * -1000 <= numbers[i] <= 1000
 * -1000 <= target <= 1000
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n) time and O(1) space, where n is the size of the input array.
 */
public class TwoSum2InputArrayIsSorted {

    public static int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;

        int currentSum = numbers[i] + numbers[j];
        while (currentSum != target) {
            if (currentSum > target) {
                j--;
            } else { // currentSum < target
                i++;
            }
            currentSum = numbers[i] + numbers[j];
        }
        return new int[]{i + 1, j + 1}; // return i + 1 and j + 1 for 1-indexed answers
    }

    public static void printExample(int[] numbers, int target, String exampleId) {
        System.out.println("twoSum" + exampleId + "=" + Arrays.toString(twoSum(numbers, target)));
    }

    public static void main(String[] args) {
        printExample(new int[]{1, 2, 3, 4}, 3, "1");
        printExample(new int[]{-1, 0}, -1, "2");
    }
}
