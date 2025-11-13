package dynamicprogramming2d;

import java.util.Arrays;

/*
 * Longest Common Subsequence
 *
 * Given two strings text1 and text2, return the length of the longest common subsequence between the two strings if one
 * exists, otherwise return 0.
 *
 * A subsequence is a sequence that can be derived from the given sequence by deleting some or no elements without
 * changing the relative order of the remaining characters.
 *
 * For example, "cat" is a subsequence of "crabt".
 * A common subsequence of two strings is a subsequence that exists in both strings.
 *
 * Example 1:
 *
 * Input: text1 = "cat", text2 = "crabt"
 * Output: 3
 * Explanation: The longest common subsequence is "cat" which has a length of 3.
 *
 * Example 2:
 *
 * Input: text1 = "abcd", text2 = "abcd"
 * Output: 4
 *
 * Example 3:
 *
 * Input: text1 = "abcd", text2 = "efgh"
 * Output: 0
 *
 * Constraints:
 *
 * 1. 1 <= text1.length, text2.length <= 1000
 * 2. text1 and text2 consist of only lowercase English characters.
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(m * n) time and O(m * n) space, where m is the length of the
 * string text1 and n is the length of the string text2.
 */
class LongestCommonSubsequence {

    private static final int UNASSIGNED = -1;

    public int longestCommonSubsequence(String text1, String text2) {
        int[][] cache = new int[text1.length() + 1][text2.length() + 1];
        for (int[] array : cache) {
            Arrays.fill(array, UNASSIGNED);
        }
        return longestCommonSubsequenceInternal(text1, text2, 0, 0, cache);
    }

    public int longestCommonSubsequenceInternal(String text1, String text2, int i, int j, int[][] cache) {
        if (cache[i][j] != UNASSIGNED) {
            return cache[i][j];
        }
        int result;
        if (i >= text1.length() || j >= text2.length()) {
            result = 0;
        } else if (text1.charAt(i) == text2.charAt(j)) {
            result = 1 + longestCommonSubsequenceInternal(text1, text2, i + 1, j + 1, cache);
        } else { // text1.charAt(i) != text2.charAt(j)
            result = Math.max(
                    longestCommonSubsequenceInternal(text1, text2, i + 1, j, cache),
                    longestCommonSubsequenceInternal(text1, text2, i, j + 1, cache)
            );
        }
        cache[i][j] = result;
        return result;
    }
}

class LongestCommonSubsequenceTester {

    public static void printExample(String text1, String text2, String exampleId) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        System.out.println("longestCommonSubsequence" + exampleId + "=" + lcs.longestCommonSubsequence(text1, text2));
    }

    public static void main(String[] args) {
        printExample("cat", "crabt", "1");
        printExample("abcd", "abcd", "2");
        printExample("abcd", "efgh", "3");
    }
}
