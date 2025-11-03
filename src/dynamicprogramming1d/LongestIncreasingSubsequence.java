package dynamicprogramming1d;

import java.util.HashMap;
import java.util.Objects;

/*
 * Longest Increasing Subsequence
 *
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * A subsequence is a sequence that can be derived from the given sequence by deleting some or no elements without
 * changing the relative order of the remaining characters.
 *
 * For example, "cat" is a subsequence of "crabt".
 * Example 1:
 *
 * Input: nums = [9,1,4,2,3,3,7]
 * Output: 4
 * Explanation: The longest increasing subsequence is [1,2,3,7], which has a length of 4.
 *
 * Example 2:
 *
 * Input: nums = [0,3,1,3,2,3]
 * Output: 4
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 1000
 * 2. -1000 <= nums[i] <= 1000
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(n ^ 2) time and O(n ^ 2) space, where n is the size of the
 * input array.
 */
class LongestIncreasingSubsequence {

    private static final int UNSELECTED = -1;

    public int lengthOfLIS(int[] nums) {
        HashMap<Input, Integer> cache = new HashMap<>();
        return lengthOfLISInternal(nums, cache, 0, UNSELECTED);
    }

    public int lengthOfLISInternal(int[] nums, HashMap<Input, Integer> cache, int i, int prevIndex) {
        Input key = new Input(i, prevIndex);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        if (i >= nums.length) {
            cache.put(key, 0);
            return 0;
        }

        int take = (prevIndex == UNSELECTED || nums[i] > nums[prevIndex])
                ? 1 + lengthOfLISInternal(nums, cache, i + 1, i)
                : 0;
        int skip = lengthOfLISInternal(nums, cache, i + 1, prevIndex);
        int max = Math.max(take, skip);
        cache.put(key, max);
        return max;
    }

    public static class Input {
        int i;
        int prevIndex;

        public Input(int i, int prevIndex) {
            this.i = i;
            this.prevIndex = prevIndex;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Input) {
                return i == ((Input) obj).i && prevIndex == ((Input) obj).prevIndex;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, prevIndex);
        }
    }
}

class LongestIncreasingSubsequenceTester {

    public static void printExample(int[] nums, String exampleId) {
        LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence();
        System.out.println("lengthOfLIS" + exampleId + "=" + longestIncreasingSubsequence.lengthOfLIS(nums));
    }

    public static void main(String[] args) {
        printExample(new int[]{9, 1, 4, 2, 3, 3, 7}, "1");
        printExample(new int[]{0, 3, 1, 3, 2, 3}, "2");
    }
}
