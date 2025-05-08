package slidingwindow;

import java.util.HashMap;

/*
 * Longest Repeating Character Replacement
 *
 * You are given a string s consisting of only uppercase english characters and an integer k. You can choose up to k
 * characters of the string and replace them with any other uppercase English character.
 *
 * After performing at most k replacements, return the length of the longest substring which contains only one distinct
 * character.
 *
 * Example 1:
 *
 * Input: s = "XYYX", k = 2
 * Output: 4
 * Explanation: Either replace the 'X's with 'Y's, or replace the 'Y's with 'X's.
 *
 * Example 2:
 *
 * Input: s = "AAABABB", k = 1
 * Output: 5
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * 0 <= k <= s.length
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n) time and O(m) space, where n is the length of the given string and m is the
 * number of unique characters in the string.
 */
public class LongestRepeatingCharacterReplacement {

    public static int characterReplacement(String s, int k) {
        int l = 0;
        int maxFrequency = 0;
        int longestSubstring = 0;
        HashMap<Character, Integer> characterFrequencies = new HashMap<>();

        for (int r = 0; r < s.length(); r++) {
            putCharacterFrequency(characterFrequencies, s.charAt(r));

            /* maxFrequency ideally represents the max current frequency present in characterFrequencies. But we can
             * optimize the solution by never decrementing it. This is achievable because decrementing maxFrequency will
             * never stop us from getting the correct longestSubstring. */
            maxFrequency = Math.max(maxFrequency, getCharacterFrequency(characterFrequencies, s.charAt(r)));

            while (getWindowSize(l, r) - maxFrequency > k) {
                removeCharacterFrequency(characterFrequencies, s.charAt(l));
                l++;
            }

            longestSubstring = Math.max(longestSubstring, getWindowSize(l, r));
        }

        return longestSubstring;
    }

    public static int getCharacterFrequency(HashMap<Character, Integer> characterFrequencies, char c) {
        return characterFrequencies.getOrDefault(c, 0);
    }

    public static void putCharacterFrequency(HashMap<Character, Integer> characterFrequencies, char c) {
        characterFrequencies.put(c, getCharacterFrequency(characterFrequencies, c) + 1);
    }

    public static void removeCharacterFrequency(HashMap<Character, Integer> characterFrequencies, char c) {
        characterFrequencies.put(c, getCharacterFrequency(characterFrequencies, c) - 1);
    }

    public static int getWindowSize(int l, int r) {
        return r - l + 1;
    }

    public static void printExample(String s, int k, String exampleId) {
        System.out.println("characterReplacement" + exampleId + "=" + characterReplacement(s, k));
    }

    public static void main(String[] args) {
        printExample("XYYX", 2, "1");
        printExample("AAABABB", 1, "2");
        printExample("AAAB", 0, "3");
    }
}
