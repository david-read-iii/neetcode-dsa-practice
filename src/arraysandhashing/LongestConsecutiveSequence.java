package arraysandhashing;

import java.util.*;

/*
 * Longest Consecutive Sequence
 *
 * Given an array of integers nums, return the length of the longest consecutive sequence of elements that can be
 * formed.
 *
 * A consecutive sequence is a sequence of elements in which each element is exactly 1 greater than the previous
 * element. The elements do not have to be consecutive in the original array.
 *
 * You must write an algorithm that runs in O(n) time.
 *
 * Example 1:
 *
 * Input: nums = [2,20,4,10,3,4,5]
 * Output: 4
 * Explanation: The longest consecutive sequence is [2, 3, 4, 5].
 *
 * Example 2:
 *
 * Input: nums = [0,3,2,5,4,6,1,1]
 * Output: 7
 *
 * Constraints:
 *
 * 0 <= nums.length <= 1000
 * -10^9 <= nums[i] <= 10^9
 *
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(n) time and O(n) space, where n is the size of the input
 * array.
 */
public class LongestConsecutiveSequence {

    // Time complexity O(n)
    // Space complexity O(n)
    public static int longestConsecutive(int[] nums) {
        Set<Integer> hashSet = toHashSet(nums);
        return findLongestConsecutive(hashSet);
    }

    public static Set<Integer> toHashSet(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        return set;
    }

    public static int findLongestConsecutive(Set<Integer> hashSet) {
        int longest = 0;
        for (int num : hashSet) {
            boolean isNumStartOfSequence = !hashSet.contains(num - 1);
            if (isNumStartOfSequence) {
                int length = 1;
                while (hashSet.contains(num + length)) {
                    length++;
                }
                longest = Math.max(longest, length);
            }
        }
        return longest;
    }

    public static void printExample(int[] nums, String exampleId) {
        System.out.println("longestConsecutive" + exampleId + "=" + longestConsecutive(nums));
    }

    public static void main(String[] args) {
        printExample(new int[]{2, 20, 4, 10, 3, 4, 5}, "1");
        printExample(new int[]{0, 3, 2, 5, 4, 6, 1, 1}, "2");
    }
}
