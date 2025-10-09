package dynamicprogramming1d;

import java.util.Set;

/*
 * Decode Ways
 *
 * A string consisting of uppercase english characters can be encoded to a number using the following mapping:
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 *
 * To decode a message, digits must be grouped and then mapped back into letters using the reverse of the mapping above.
 * There may be multiple ways to decode a message. For example, "1012" can be mapped into:
 *
 * - "JAB" with the grouping (10 1 2)
 * - "JL" with the grouping (10 12)
 *
 * The grouping (1 01 2) is invalid because 01 cannot be mapped into a letter since it contains a leading zero.
 *
 * Given a string s containing only digits, return the number of ways to decode it. You can assume that the answer fits
 * in a 32-bit integer.
 *
 * Example 1:
 *
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 *
 * Example 2:
 *
 * Input: s = "01"
 * Output: 0
 * Explanation: "01" cannot be decoded because "01" cannot be mapped into a letter.
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 100
 * 2. s consists of digits
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(n) time and O(n) space, where n is the length of the given
 * string.
 */
class DecodeWays {

    Set<String> validDecodings = Set.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26");

    public int numDecodings(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        // dp[i] means the number of ways to decode substring s[0..i]
        int[] dp = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (i == 0) {
                dp[0] = s.charAt(0) != '0'
                        ? 1
                        : 0;
            } else {
                int numDecodings = 0;
                // One-digit check.
                if (validDecodings.contains(s.substring(i, i + 1))) {
                    numDecodings += dp[i - 1];
                }
                // Two-digit check.
                if (validDecodings.contains(s.substring(i - 1, i + 1))) {
                    numDecodings += i - 2 >= 0
                            ? dp[i - 2]
                            : 1;
                }
                dp[i] = numDecodings;
            }
        }
        return dp[dp.length - 1];
    }
}

class DecodeWaysTester {

    public static void printExample(String s, String exampleId) {
        DecodeWays decodeWays = new DecodeWays();
        System.out.println("numDecodings" + exampleId + "=" + decodeWays.numDecodings(s));
    }

    public static void main(String[] args) {
        printExample("12", "1");
        printExample("01", "2");
    }
}
