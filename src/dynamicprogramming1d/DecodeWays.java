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
// TODO: Solve this using a dynamic programming approach. Not brute force.
class DecodeWays {

    int count = 0;
    Set<String> validDecodings = Set.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26");

    public int numDecodings(String s) {
        exploreDecodings(0, 1, s);
        exploreDecodings(0, 2, s);
        return count;
    }

    private void exploreDecodings(int index, int countChars, String s) {
        if (isValidDecoding(index, countChars, s)) {
            if (index >= s.length() - countChars) {
                count++;
            } else {
                int newIndex = index + countChars;
                exploreDecodings(newIndex, 1, s);
                exploreDecodings(newIndex, 2, s);
            }
        }
    }

    private boolean isValidDecoding(int index, int countChars, String s) {
        try {
            String substring = s.substring(index, index + countChars);
            return validDecodings.contains(substring);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
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
