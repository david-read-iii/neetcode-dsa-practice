package slidingwindow;

import java.util.HashSet;

/*
 * Longest Substring Without Repeating Characters
 *
 * Given a string s, find the length of the longest substring without duplicate characters.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 * Example 1:
 *
 * Input: s = "zxyzxyz"
 * Output: 3
 *
 * Explanation: The string "xyz" is the longest without duplicate characters.
 *
 * Example 2:
 *
 * Input: s = "xxxx"
 * Output: 1
 *
 * Constraints:
 *
 * 0 <= s.length <= 1000
 * s may consist of printable ASCII characters.
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n) time and O(m) space, where n is the length of the string and m is the number
 * of unique characters in the string.
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring(String s) {
        HashSet<Character> charactersEncountered = new HashSet<>();
        int i = 0;
        int j = 0;
        int longestSubstring = 0;

        while (j < s.length()) {
            while (charactersEncountered.contains(s.charAt(j))) {
                charactersEncountered.remove(s.charAt(i));
                i++;
            }

            charactersEncountered.add(s.charAt(j));

            longestSubstring = Math.max(j - i + 1, longestSubstring);

            j++;
        }

        return longestSubstring;
    }

    public static void printExample(String s, String exampleId) {
        System.out.println("lengthOfLongestSubstring" + exampleId + "=" + lengthOfLongestSubstring(s));
    }

    public static void main(String[] args) {
        printExample("zxyzxyz", "1");
        printExample("xxxx", "2");
        printExample("bbbbb", "3");
        printExample("pwwkew", "4");
        printExample(" ", "5");
    }
}
